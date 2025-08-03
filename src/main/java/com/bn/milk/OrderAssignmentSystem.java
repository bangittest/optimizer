package com.bn.milk;

import com.bn.milk.criteria.BaseCriteria;
import com.bn.milk.criteria.CriteriaManager;
import com.bn.milk.repository.DatabaseManager;

import java.util.Map;

public class OrderAssignmentSystem {
    private CriteriaManager criteriaManager;
    private DatabaseManager databaseManager;
    private OrderAssignmentOptimizer optimizer;
    private FakeDataOptimizer fakeOptimizer;

    public OrderAssignmentSystem(String dbConnectionString, String dbUsername, String dbPassword) {
        this.criteriaManager = new CriteriaManager();
        this.databaseManager = new DatabaseManager(dbConnectionString, dbUsername, dbPassword);
        this.optimizer = new OrderAssignmentOptimizer(criteriaManager, databaseManager);
        this.fakeOptimizer = new FakeDataOptimizer(criteriaManager);
    }

    public OrderAssignmentSystem() {
        // Constructor cho test với fake data
        this.criteriaManager = new CriteriaManager();
        this.fakeOptimizer = new FakeDataOptimizer(criteriaManager);
        this.optimizer = new OrderAssignmentOptimizer(criteriaManager, null);
    }

    public void runFakeDataTest() {
        System.out.println("========================================");
        System.out.println("BẮT ĐẦU TEST VỚI FAKE DATA");
        System.out.println("========================================");

        // Hiển thị cấu hình tiêu chí hiện tại
        System.out.println("\n=== CẤU HÌNH TIÊU CHÍ ===");
        for (BaseCriteria criteria : criteriaManager.getCriteriaList()) {
            System.out.printf("%s: %.2f%n", criteria.getName(), criteria.getWeight());
        }

        Map<Integer, OrderAssignmentOptimizer.VehicleDriverAssignment> assignments =
                fakeOptimizer.optimizeWithFakeData();

        if (assignments.isEmpty()) {
            System.out.println("Không tìm thấy phương án tối ưu.");
        } else {
            System.out.println("\n=== TỔNG KẾT ===");
            System.out.printf("Đã phân bổ %d/%d đơn hàng thành công.%n",
                    assignments.size(), FakeDataGenerator.generateFakeOrders().size());
        }

        System.out.println("========================================");
        System.out.println("KẾT THÚC TEST");
        System.out.println("========================================");
    }

    public void runSplittingAndConsolidationTest() {
        System.out.println("========================================");
        System.out.println("BẮT ĐẦU TEST TÁCH/GỘP ĐƠN HÀNG");
        System.out.println("========================================");

        // Hiển thị cấu hình tiêu chí hiện tại
        System.out.println("\n=== CẤU HÌNH TIÊU CHÍ ===");
        for (BaseCriteria criteria : criteriaManager.getCriteriaList()) {
            System.out.printf("%s: %.2f%n", criteria.getName(), criteria.getWeight());
        }

        Map<Integer, OrderAssignmentOptimizer.VehicleDriverAssignment> assignments =
                optimizer.optimizeWithSplittingAndConsolidation();

        if (assignments.isEmpty()) {
            System.out.println("Không tìm thấy phương án tối ưu.");
        } else {
            System.out.println("\n=== TỔNG KẾT TÁCH/GỘP ===");
            System.out.printf("Đã phân bổ %d đơn hàng (bao gồm đơn con) thành công.%n", assignments.size());
        }

        System.out.println("========================================");
        System.out.println("KẾT THÚC TEST TÁCH/GỘP");
        System.out.println("========================================");
    }

    public void runOptimization() {
        System.out.println("Bắt đầu tối ưu hóa phân bổ đơn hàng...");

        Map<Integer, OrderAssignmentOptimizer.VehicleDriverAssignment> assignments =
                optimizer.optimizeAssignments();

        if (assignments.isEmpty()) {
            System.out.println("Không tìm thấy phương án tối ưu hoặc không có dữ liệu.");
            return;
        }

        System.out.println("Kết quả tối ưu hóa:");
        for (Map.Entry<Integer, OrderAssignmentOptimizer.VehicleDriverAssignment> entry : assignments.entrySet()) {
            int orderId = entry.getKey();
            OrderAssignmentOptimizer.VehicleDriverAssignment assignment = entry.getValue();

            System.out.printf("Đơn hàng %d -> Xe %d, Tài xế %d%n",
                    orderId, assignment.getVehicleId(), assignment.getDriverId());

            // Cập nhật database
            if (databaseManager != null) {
                databaseManager.updateOrderAssignment(orderId, assignment.getVehicleId(), assignment.getDriverId());
            }
        }

        System.out.println("Hoàn thành tối ưu hóa và cập nhật database.");
    }

    // Phương thức để thay đổi trọng số tiêu chí
    public void updateCriteriaWeight(String criteriaName, double newWeight) {
        criteriaManager.updateCriteriaWeight(criteriaName, newWeight);
        System.out.printf("Đã cập nhật trọng số của %s thành %.2f%n", criteriaName, newWeight);
    }

    // Main method để test
    public static void main(String[] args) {
        OrderAssignmentSystem system = new OrderAssignmentSystem();

        // Test 1: Cơ bản với fake data
        system.runFakeDataTest();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("TEST TÁCH VÀ GỘP ĐƠN HÀNG");
        System.out.println("=".repeat(50));

        // Test 2: Tách và gộp đơn hàng
        system.runSplittingAndConsolidationTest();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("THAY ĐỔI TRỌNG SỐ VÀ TEST LẠI");
        System.out.println("=".repeat(50));

        // Thay đổi trọng số để ưu tiên việc gộp đơn
        system.updateCriteriaWeight("Sub Order Grouping", 0.3);
        system.updateCriteriaWeight("Vehicle Utilization", 0.2);
        system.updateCriteriaWeight("Distance", 0.15);

        // Test lại với cấu hình mới
        system.runSplittingAndConsolidationTest();
    }
}
