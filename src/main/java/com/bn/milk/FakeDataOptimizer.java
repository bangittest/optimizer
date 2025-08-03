package com.bn.milk;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;
import com.bn.milk.model.VehicleDriverTeam;
import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.bn.milk.criteria.BaseCriteria;
import com.bn.milk.criteria.CriteriaManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeDataOptimizer {
    private CriteriaManager criteriaManager;

    public FakeDataOptimizer(CriteriaManager criteriaManager) {
        this.criteriaManager = criteriaManager;
    }

    public Map<Integer, OrderAssignmentOptimizer.VehicleDriverAssignment> optimizeWithFakeData() {
        // Load fake data
        List<Order> orders = FakeDataGenerator.generateFakeOrders();
        List<Vehicle> vehicles = FakeDataGenerator.generateFakeVehicles();
        List<Driver> drivers = FakeDataGenerator.generateFakeDrivers();
        List<VehicleDriverTeam> teams = FakeDataGenerator.generateFakeTeams();

        System.out.println("=== FAKE DATA ===");
        System.out.println("Đơn hàng:");
        for (Order order : orders) {
            System.out.printf("  %s: %s -> Trọng lượng: %.1f, Ưu tiên: %d, ETD: %s%n",
                    order.getOrderCode(), "Customer" + order.getCustomerId(),
                    order.getWeight(), order.getPrecedence(),
                    order.getEtdDate().toString().substring(0, 16));
        }

        System.out.println("\nXe:");
        for (Vehicle vehicle : vehicles) {
            System.out.printf("  %s: Tải trọng %.1f, Vị trí: (%.4f, %.4f)%n",
                    vehicle.getRegNo(), vehicle.getWeight(),
                    vehicle.getLatitude(), vehicle.getLongitude());
        }

        System.out.println("\nTài xế:");
        for (Driver driver : drivers) {
            System.out.printf("  %s: %s - Đơn hiện tại: %d%n",
                    driver.getCode(), driver.getFullName(), driver.getCurrentOrdersCount());
        }

        // Tạo mapping vehicle-driver teams
        Map<Integer, List<Integer>> vehicleToDrivers = new HashMap<>();
        for (VehicleDriverTeam team : teams) {
            vehicleToDrivers.computeIfAbsent(team.getVehicleId(), k -> new ArrayList<>())
                    .add(team.getDriverId());
        }

        // Khởi tạo OR-Tools
        Loader.loadNativeLibraries();
        MPSolver solver = MPSolver.createSolver("SCIP");
        if (solver == null) {
            System.err.println("Could not create solver SCIP");
            return new HashMap<>();
        }

        // Tạo variables
        Map<String, MPVariable> variables = new HashMap<>();

        for (Order order : orders) {
            for (Vehicle vehicle : vehicles) {
                List<Integer> availableDrivers = vehicleToDrivers.get(vehicle.getId());
                if (availableDrivers != null) {
                    for (Integer driverId : availableDrivers) {
                        Driver driver = drivers.stream()
                                .filter(d -> d.getId() == driverId)
                                .findFirst()
                                .orElse(null);
                        if (driver != null) {
                            String varName = String.format("x_%d_%d_%d", order.getId(), vehicle.getId(), driver.getId());
                            variables.put(varName, solver.makeIntVar(0, 1, varName));
                        }
                    }
                }
            }
        }

        // Constraints
        addConstraintsForFakeData(solver, variables, orders, vehicles, drivers, vehicleToDrivers);

        // Objective function
        setObjectiveFunctionForFakeData(solver, variables, orders, vehicles, drivers);

        // Solve
        MPSolver.ResultStatus resultStatus = solver.solve();

        Map<Integer, OrderAssignmentOptimizer.VehicleDriverAssignment> assignments = new HashMap<>();
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            System.out.println("\n=== KẾT QUẢ TỐI ƯU ===");

            // Extract solution
            for (Map.Entry<String, MPVariable> entry : variables.entrySet()) {
                if (entry.getValue().solutionValue() > 0.5) {
                    String[] parts = entry.getKey().split("_");
                    int orderId = Integer.parseInt(parts[1]);
                    int vehicleId = Integer.parseInt(parts[2]);
                    int driverId = Integer.parseInt(parts[3]);

                    assignments.put(orderId, new OrderAssignmentOptimizer.VehicleDriverAssignment(vehicleId, driverId));

                    // Tìm thông tin chi tiết để hiển thị
                    Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().orElse(null);
                    Vehicle vehicle = vehicles.stream().filter(v -> v.getId() == vehicleId).findFirst().orElse(null);
                    Driver driver = drivers.stream().filter(d -> d.getId() == driverId).findFirst().orElse(null);

                    if (order != null && vehicle != null && driver != null) {
                        double score = criteriaManager.calculateTotalScore(order, vehicle, driver);
                        System.out.printf("Đơn %s -> Xe %s + Tài xế %s (Điểm: %.4f)%n",
                                order.getOrderCode(), vehicle.getRegNo(), driver.getFullName(), score);

                        // Hiển thị chi tiết các tiêu chí
                        System.out.println("  Chi tiết điểm:");
                        for (BaseCriteria criteria : criteriaManager.getCriteriaList()) {
                            double criteriaScore = criteria.calculate(order, vehicle, driver);
                            System.out.printf("    %s: %.4f (trọng số: %.2f) = %.4f%n",
                                    criteria.getName(), criteriaScore, criteria.getWeight(),
                                    criteriaScore * criteria.getWeight());
                        }
                        System.out.println();
                    }
                }
            }

            System.out.printf("Objective value: %.4f%n", solver.objective().value());
        } else {
            System.out.println("Không tìm thấy solution tối ưu. Status: " + resultStatus);
        }

        return assignments;
    }

    private void addConstraintsForFakeData(MPSolver solver, Map<String, MPVariable> variables,
                                           List<Order> orders, List<Vehicle> vehicles, List<Driver> drivers,
                                           Map<Integer, List<Integer>> vehicleToDrivers) {

        // Constraint 1: Mỗi order chỉ được assign cho tối đa 1 vehicle-driver pair
        for (Order order : orders) {
            MPConstraint constraint = solver.makeConstraint(0, 1, "order_" + order.getId());

            for (Vehicle vehicle : vehicles) {
                List<Integer> availableDrivers = vehicleToDrivers.get(vehicle.getId());
                if (availableDrivers != null) {
                    for (Integer driverId : availableDrivers) {
                        String varName = String.format("x_%d_%d_%d", order.getId(), vehicle.getId(), driverId);
                        MPVariable var = variables.get(varName);
                        if (var != null) {
                            constraint.setCoefficient(var, 1);
                        }
                    }
                }
            }
        }

        // Constraint 2: Capacity constraints cho vehicles
        for (Vehicle vehicle : vehicles) {
            MPConstraint weightConstraint = solver.makeConstraint(0, vehicle.getWeight(),
                    "vehicle_weight_" + vehicle.getId());
            MPConstraint volumeConstraint = solver.makeConstraint(0, vehicle.getVolume(),
                    "vehicle_volume_" + vehicle.getId());

            for (Order order : orders) {
                List<Integer> availableDrivers = vehicleToDrivers.get(vehicle.getId());
                if (availableDrivers != null) {
                    for (Integer driverId : availableDrivers) {
                        String varName = String.format("x_%d_%d_%d", order.getId(), vehicle.getId(), driverId);
                        MPVariable var = variables.get(varName);
                        if (var != null) {
                            weightConstraint.setCoefficient(var, order.getWeight());
                            volumeConstraint.setCoefficient(var, order.getVolume());
                        }
                    }
                }
            }
        }
    }

    private void setObjectiveFunctionForFakeData(MPSolver solver, Map<String, MPVariable> variables,
                                                 List<Order> orders, List<Vehicle> vehicles, List<Driver> drivers) {
        MPObjective objective = solver.objective();
        objective.setMaximization();

        for (Order order : orders) {
            for (Vehicle vehicle : vehicles) {
                for (Driver driver : drivers) {
                    String varName = String.format("x_%d_%d_%d", order.getId(), vehicle.getId(), driver.getId());
                    MPVariable var = variables.get(varName);
                    if (var != null) {
                        double score = criteriaManager.calculateTotalScore(order, vehicle, driver);
                        objective.setCoefficient(var, score);
                    }
                }
            }
        }
    }
}
