package com.bn.optimizer.service;

import com.bn.optimizer.dto.*;
import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderAssignmentOptimizer {
    private final CriteriaManager criteriaManager;
    private final MPSolver solver;
    private final Map<String, MPVariable> xVars = new HashMap<>(); // Biến quyết định x_o_v_d
    private final int K_NEAREST = 5; // Số lượng vehicle gần nhất (K trong KNN)

    public OrderAssignmentOptimizer(CriteriaManager criteriaManager) {
        this.criteriaManager = criteriaManager;
        Loader.loadNativeLibraries(); // Load thư viện OR-Tools native
        this.solver = Optional.ofNullable(MPSolver.createSolver("SCIP"))
                .orElseThrow(() -> new IllegalStateException("Cannot instantiate SCIP solver"));
    }

    /**
     * Hàm chính: tạo model, solve và trả về kết quả gán đơn hàng cho team (xe + tài xế)
     */
    public Map<Integer, VehicleDriverAssignment> optimize(List<OrderDTO> orders, List<VehicleDTO> vehicles, List<DriverDTO> drivers, List<DriverVehicleTeamDTO> teams) {

        // 2. In dữ liệu đầu vào
        printDataOverview(orders, vehicles, drivers);

        // 3. Map vehicle -> danh sách driver của nó (nhóm team)
        Map<Integer, List<DriverDTO>> vehicleDrivers = buildVehicleDriversMap(vehicles, drivers, teams);

        // 4. Tạo biến quyết định x_o_v_d nhưng CHỈ CHO K vehicle gần order (KNN)
        createDecisionVariables(orders, vehicles, vehicleDrivers);

        // 5. Ràng buộc mỗi order gán nhiều nhất 1 team
        addOrderAssignmentConstraints(orders, vehicles, vehicleDrivers);

        // 6. Ràng buộc capacity của mỗi vehicle (theo weight, volume)
        addVehicleCapacityConstraints(orders, vehicles, vehicleDrivers);

        // 7. Mục tiêu: tối đa hóa tổng điểm gán (theo các tiêu chí)
        configureObjective(orders, vehicles, drivers);

        // 8. Solve và trích xuất kết quả
        return solveAndExtract(orders, vehicles, drivers);
    }

    // Tạo map: vehicleId -> List<Driver> thuộc team đó
    private Map<Integer, List<DriverDTO>> buildVehicleDriversMap(
            List<VehicleDTO> vehicles,
            List<DriverDTO> drivers,
            List<DriverVehicleTeamDTO> teams) {

        // Map driverId -> DriverDTO
        Map<Integer, DriverDTO> driverById = drivers.stream()
                .filter(d -> d != null && d.getId() != null)
                .collect(Collectors.toMap(DriverDTO::getId, Function.identity()));

        return teams.stream()
                .filter(t -> t != null && t.getVehicleId() != null && t.getDriverId() != null)
                .map(t -> {
                    DriverDTO driver = driverById.get(t.getDriverId());
                    return driver != null
                            ? new AbstractMap.SimpleEntry<>(t.getVehicleId(), driver)
                            : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }

    // Tạo biến quyết định x_o_v_d cho K vehicle gần nhất
    private void createDecisionVariables(List<OrderDTO> orders, List<VehicleDTO> vehicles, Map<Integer, List<DriverDTO>> vehicleDrivers) {
        for (OrderDTO o : orders) {
            // Tìm K vehicle gần order (dùng KNN theo tọa độ)
            List<VehicleDTO> nearestVehicles = findKNearestVehicles(o, vehicles, K_NEAREST);
            for (VehicleDTO v : nearestVehicles) {
                for (DriverDTO d : vehicleDrivers.getOrDefault(v.getId(), Collections.emptyList())) {
                    String var = String.format("x_%d_%d_%d", o.getId(), v.getId(), d.getId());
                    xVars.put(var, solver.makeIntVar(0, 1, var)); // biến nhị phân 0 hoặc 1
                }
            }
        }
    }

    // Ràng buộc: mỗi order chỉ được gán tối đa 1 team
    private void addOrderAssignmentConstraints(List<OrderDTO> orders, List<VehicleDTO> vehicles, Map<Integer, List<DriverDTO>> vehicleDrivers) {
        for (OrderDTO o : orders) {
            MPConstraint c = solver.makeConstraint(0, 1, "orderMax_" + o.getId());
            xVars.entrySet().stream()
                    .filter(e -> e.getKey().startsWith("x_" + o.getId() + "_"))
                    .forEach(e -> c.setCoefficient(e.getValue(), 1));
        }
    }


    private void addVehicleCapacityConstraints(List<OrderDTO> orders, List<VehicleDTO> vehicles, Map<Integer, List<DriverDTO>> vehicleDrivers) {
        if (vehicles == null || orders == null || vehicleDrivers == null) return;

        for (VehicleDTO v : vehicles) {
            if (v == null || v.getWeight() == null || v.getVolume() == null) {
                continue;
            }
            MPConstraint wtC = solver.makeConstraint(0, v.getWeight(), "wtCap_" + v.getId());
            MPConstraint volC = solver.makeConstraint(0, v.getVolume(), "volCap_" + v.getId());
            for (OrderDTO o : orders) {
                if (o == null || o.getWeight() == null || o.getVolume() == null) {
                    continue;
                }
                List<DriverDTO> drivers = vehicleDrivers.getOrDefault(v.getId(), Collections.emptyList());
                if (drivers == null) {
                    continue;
                }
                for (DriverDTO d : drivers) {
                    if (d == null) {
                        continue;
                    }
                    MPVariable var = getVar(o, v, d);
                    if (var != null) {
                        wtC.setCoefficient(var, o.getWeight());
                        volC.setCoefficient(var, o.getVolume());
                    }
                }
            }
        }
    }


    // Hàm mục tiêu: tối đa hóa tổng điểm theo tiêu chí
    private void configureObjective(List<OrderDTO> orders, List<VehicleDTO> vehicles, List<DriverDTO> drivers) {
        MPObjective obj = solver.objective();
        obj.setMaximization();

        xVars.forEach((key, var) -> {
            String[] parts = key.split("_");
            int oid = Integer.parseInt(parts[1]);
            int vid = Integer.parseInt(parts[2]);
            int did = Integer.parseInt(parts[3]);

            OrderDTO o = findById(orders, oid, OrderDTO::getId);
            VehicleDTO v = findById(vehicles, vid, VehicleDTO::getId);
            DriverDTO d = findById(drivers, did, DriverDTO::getId);

            if (o != null && v != null && d != null) {
                double score = criteriaManager.calculateTotalScore(o, v, d);
                obj.setCoefficient(var, score);
            }
        });
    }

    // Solve và extract kết quả
    private Map<Integer, VehicleDriverAssignment> solveAndExtract(List<OrderDTO> orders, List<VehicleDTO> vehicles, List<DriverDTO> drivers) {
        Map<Integer, VehicleDriverAssignment> result = new HashMap<>();
        if (solver.solve() == MPSolver.ResultStatus.OPTIMAL) {
            System.out.println("\n=== OPTIMAL SOLUTION ===");
            xVars.forEach((name, var) -> {
                if (var.solutionValue() > 0.5) {
                    String[] p = name.split("_");
                    int oid = Integer.parseInt(p[1]);
                    int vid = Integer.parseInt(p[2]);
                    int did = Integer.parseInt(p[3]);
                    result.put(oid, new VehicleDriverAssignment(vid, did));
                    printAssignmentDetail(oid, vid, did, orders, vehicles, drivers);
                }
            });
            System.out.printf("Objective: %.4f\n", solver.objective().value());
        } else {
            System.err.println("No optimal solution found.");
        }
        return result;
    }

    // Truy xuất biến x_o_v_d
    private MPVariable getVar(OrderDTO o, VehicleDTO v, DriverDTO d) {
        return xVars.get(String.format("x_%d_%d_%d", o.getId(), v.getId(), d.getId()));
    }

    // In chi tiết gán và điểm của từng tiêu chí
    private void printAssignmentDetail(int oid, int vid, int did, List<OrderDTO> orders, List<VehicleDTO> vehicles, List<DriverDTO> drivers) {
        OrderDTO o = findById(orders, oid, OrderDTO::getId);
        VehicleDTO v = findById(vehicles, vid, VehicleDTO::getId);
        DriverDTO d = findById(drivers, did, DriverDTO::getId);
        if (o != null && v != null && d != null) {
            double score = criteriaManager.calculateTotalScore(o, v, d);
            System.out.printf("Order %s -> Vehicle %s + Driver %s (Score: %.4f)\n",
                    o.getOrderCode(), v.getRegNo(), d.getFullName(), score);
            for (CriteriaDTO c : criteriaManager.getCriteriaList()) {
                double sc = c.calculate(o, v, d);
                System.out.printf("   - %s: %.4f * %.2f = %.4f\n",
                        c.getName(), sc, c.getWeight(), sc * c.getWeight());
            }
        }
    }

    // tìm K vehicle gần nhất với order dựa trên khoảng cách Haversine
    private List<VehicleDTO> findKNearestVehicles(OrderDTO order, List<VehicleDTO> vehicles, int k) {
        if (order.getLatitudeDest() == null || order.getLongitudeDest() == null) {
            return Collections.emptyList();
        }
        return vehicles.stream()
                .filter(v -> v != null && v.getLatitude() != null && v.getLongitude() != null)
                .sorted(Comparator.comparingDouble(v -> haversine(
                        order.getLatitudeDest(), order.getLongitudeDest(),
                        v.getLatitude(), v.getLongitude())))
                .limit(k)
                .collect(Collectors.toList());
    }

    // Công thức tính khoảng cách Haversine giữa 2 tọa độ (dùng để ước tính km bay thẳng)
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Bán kính Trái Đất (km)
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // Tìm object theo id
    private <T> T findById(List<T> list, int id, Function<T, Integer> idGetter) {
        return list.stream()
                .filter(e -> Objects.equals(idGetter.apply(e), id))
                .findFirst()
                .orElse(null);
    }

    // In dữ liệu đầu vào
    private void printDataOverview(List<OrderDTO> orders, List<VehicleDTO> vehicles, List<DriverDTO> drivers) {
        System.out.println("=== DATA ORDER ===\nOrders:");
        orders.forEach(o -> System.out.println(formatOrder(o)));
        System.out.println("\nVehicles:");
        vehicles.forEach(v -> System.out.println(formatVehicle(v)));
        System.out.println("\nDrivers:");
        drivers.forEach(d -> System.out.println(formatDriver(d)));
    }

    // Format hiển thị order
    private String formatOrder(OrderDTO o) {
        return String.format("  %s (cust:%d, wt:%.1f, prio:%d, ETD:%s)",
                o.getOrderCode(), o.getCustomerId(), o.getWeight(), o.getPrecedence(),
                o.getEtdDate().toString().substring(0, 16));
    }

    private String formatVehicle(VehicleDTO v) {
        return String.format("  %s (cap:%.1f/%.1f, loc:(%.4f,%.4f))",
                v.getRegNo(), v.getWeight(), v.getVolume(), v.getLatitude(), v.getLongitude());
    }

    private String formatDriver(DriverDTO d) {
        return String.format("  %s (%s, currOrders:%d)",
                d.getCode(), d.getFullName(), d.getCurrentOrdersCount());
    }
}
