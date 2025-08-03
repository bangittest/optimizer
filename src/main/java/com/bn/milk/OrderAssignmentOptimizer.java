package com.bn.milk;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;
import com.bn.milk.model.VehicleDriverTeam;
import com.bn.milk.repository.DatabaseManager;
import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.bn.milk.criteria.CriteriaManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAssignmentOptimizer {
    private CriteriaManager criteriaManager;
    private DatabaseManager databaseManager;

    public OrderAssignmentOptimizer(CriteriaManager criteriaManager, DatabaseManager databaseManager) {
        this.criteriaManager = criteriaManager;
        this.databaseManager = databaseManager;
    }

    public Map<Integer, VehicleDriverAssignment> optimizeAssignments() {
        // Load data từ database
        List<Order> orders = databaseManager.getUnassignedOrders();
        List<Vehicle> vehicles = databaseManager.getAvailableVehicles();
        List<Driver> drivers = databaseManager.getAvailableDrivers();
        List<VehicleDriverTeam> teams = databaseManager.getVehicleDriverTeams();

        if (orders.isEmpty() || vehicles.isEmpty() || drivers.isEmpty()) {
            return new HashMap<>();
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

        // Tạo variables: x[o][v][d] = 1 nếu order o được assign cho vehicle v và driver d
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
        addConstraints(solver, variables, orders, vehicles, drivers, vehicleToDrivers);

        // Objective function
        setObjectiveFunction(solver, variables, orders, vehicles, drivers);

        // Solve
        MPSolver.ResultStatus resultStatus = solver.solve();

        Map<Integer, VehicleDriverAssignment> assignments = new HashMap<>();
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            // Extract solution
            for (Map.Entry<String, MPVariable> entry : variables.entrySet()) {
                if (entry.getValue().solutionValue() > 0.5) {
                    String[] parts = entry.getKey().split("_");
                    int orderId = Integer.parseInt(parts[1]);
                    int vehicleId = Integer.parseInt(parts[2]);
                    int driverId = Integer.parseInt(parts[3]);

                    assignments.put(orderId, new VehicleDriverAssignment(vehicleId, driverId));
                }
            }
        }

        return assignments;
    }

    public Map<Integer, VehicleDriverAssignment> optimizeWithSplittingAndConsolidation() {
        // Load fake data
        List<Order> originalOrders = FakeDataGenerator.generateFakeOrdersWithSplitting();
        List<Vehicle> vehicles = FakeDataGenerator.generateFakeVehicles();
        List<Driver> drivers = FakeDataGenerator.generateFakeDrivers();
        List<VehicleDriverTeam> teams = FakeDataGenerator.generateFakeTeams();

        System.out.println("=== FAKE DATA VỚI TÁCH/GỘP ĐƠN ===");
        System.out.println("Đơn hàng gốc:");
        for (Order order : originalOrders) {
            System.out.printf("  %s: Trọng lượng: %.1fkg, Thể tích: %.1fm³%n",
                    order.getOrderCode(), order.getWeight(), order.getVolume());
        }

        // Bước 1: Tách đơn hàng lớn
        List<Order> allOrders = new ArrayList<>();
        for (Order order : originalOrders) {
            List<Order> splitOrders = OrderSplittingService.splitOrder(order, vehicles);
            allOrders.addAll(splitOrders);
        }

        System.out.println("\nSau khi tách đơn:");
        for (Order order : allOrders) {
            if (order.isSubOrder()) {
                System.out.printf("  %s: Trọng lượng: %.1fkg, Thể tích: %.1fm³ (Đơn con %d/%d)%n",
                        order.getSubOrderCode(), order.getWeight(), order.getVolume(),
                        order.getSplitIndex(), order.getSplitIndex());
            } else {
                System.out.printf("  %s: Trọng lượng: %.1fkg, Thể tích: %.1fm³ (Không tách)%n",
                        order.getOrderCode(), order.getWeight(), order.getVolume());
            }
        }

        // Bước 2: Gộp đơn vào xe (consolidation)
        Map<Integer, List<Order>> consolidation = OrderSplittingService.consolidateOrders(allOrders, vehicles);

        System.out.println("\nSau khi gộp đơn vào xe:");
        for (Map.Entry<Integer, List<Order>> entry : consolidation.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                Vehicle vehicle = vehicles.stream().filter(v -> v.getId() == entry.getKey()).findFirst().orElse(null);
                if (vehicle != null) {
                    double totalWeight = entry.getValue().stream().mapToDouble(Order::getWeight).sum();
                    double totalVolume = entry.getValue().stream().mapToDouble(Order::getVolume).sum();
                    System.out.printf("  Xe %s: %d đơn, Tổng trọng lượng: %.1fkg/%.1fkg, Thể tích: %.1fm³/%.1fm³%n",
                            vehicle.getRegNo(), entry.getValue().size(),
                            totalWeight, vehicle.getWeight(),
                            totalVolume, vehicle.getVolume());

                    for (Order order : entry.getValue()) {
                        String orderName = order.isSubOrder() ? order.getSubOrderCode() : order.getOrderCode();
                        System.out.printf("    - %s (%.1fkg, %.1fm³)%n",
                                orderName, order.getWeight(), order.getVolume());
                    }
                }
            }
        }

        // Bước 3: Tối ưu hóa assignment với OR-Tools
        return optimizeWithProcessedOrders(allOrders, vehicles, drivers, teams);
    }

    private Map<Integer, VehicleDriverAssignment> optimizeWithProcessedOrders(
            List<Order> orders, List<Vehicle> vehicles, List<Driver> drivers, List<VehicleDriverTeam> teams) {

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
        addConstraints(solver, variables, orders, vehicles, drivers, vehicleToDrivers);

        // Objective function
        setObjectiveFunction(solver, variables, orders, vehicles, drivers);

        // Solve
        MPSolver.ResultStatus resultStatus = solver.solve();

        Map<Integer, VehicleDriverAssignment> assignments = new HashMap<>();
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            System.out.println("\n=== KẾT QUẢ TỐI ƯU (TÁCH/GỘP) ===");

            // Extract solution
            for (Map.Entry<String, MPVariable> entry : variables.entrySet()) {
                if (entry.getValue().solutionValue() > 0.5) {
                    String[] parts = entry.getKey().split("_");
                    int orderId = Integer.parseInt(parts[1]);
                    int vehicleId = Integer.parseInt(parts[2]);
                    int driverId = Integer.parseInt(parts[3]);

                    assignments.put(orderId, new VehicleDriverAssignment(vehicleId, driverId));

                    // Tìm thông tin chi tiết để hiển thị
                    Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().orElse(null);
                    Vehicle vehicle = vehicles.stream().filter(v -> v.getId() == vehicleId).findFirst().orElse(null);
                    Driver driver = drivers.stream().filter(d -> d.getId() == driverId).findFirst().orElse(null);

                    if (order != null && vehicle != null && driver != null) {
                        double score = criteriaManager.calculateTotalScore(order, vehicle, driver);
                        String orderName = order.isSubOrder() ? order.getSubOrderCode() : order.getOrderCode();
                        System.out.printf("%s -> Xe %s + Tài xế %s (Điểm: %.4f)%n",
                                orderName, vehicle.getRegNo(), driver.getFullName(), score);
                    }
                }
            }

            System.out.printf("Objective value: %.4f%n", solver.objective().value());
        } else {
            System.out.println("Không tìm thấy solution tối ưu. Status: " + resultStatus);
        }

        return assignments;
    }

    private void addConstraints(MPSolver solver, Map<String, MPVariable> variables,
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

    private void setObjectiveFunction(MPSolver solver, Map<String, MPVariable> variables,
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

    public static class VehicleDriverAssignment {
        private int vehicleId;
        private int driverId;

        public VehicleDriverAssignment(int vehicleId, int driverId) {
            this.vehicleId = vehicleId;
            this.driverId = driverId;
        }

        public int getVehicleId() { return vehicleId; }
        public int getDriverId() { return driverId; }

        @Override
        public String toString() {
            return String.format("VehicleId: %d, DriverId: %d", vehicleId, driverId);
        }
    }
}
