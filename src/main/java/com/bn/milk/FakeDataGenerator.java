package com.bn.milk;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;
import com.bn.milk.model.VehicleDriverTeam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FakeDataGenerator {
    public static List<Order> generateFakeOrdersWithSplitting() {
        List<Order> orders = new ArrayList<>();

        // Tạo 2 đơn hàng lớn cần tách + 3 đơn hàng bình thường

        // Đơn lớn 1: HN -> HCM (5000kg, cần tách)
        orders.add(new Order(1, "ORD001", 101, 1, 2, 5000.0, 150.0, 9,
                LocalDateTime.now().plusHours(12), 21.0285, 105.8542, 10.7769, 106.7009));

        // Đơn lớn 2: HCM -> Cần Thơ (4500kg, cần tách)
        orders.add(new Order(2, "ORD002", 102, 3, 4, 4500.0, 180.0, 8,
                LocalDateTime.now().plusHours(18), 10.7769, 106.7009, 10.0452, 105.7469));

        // Đơn bình thường
        orders.add(new Order(3, "ORD003", 103, 5, 6, 800.0, 30.0, 5,
                LocalDateTime.now().plusHours(6), 16.4637, 107.5909, 10.0452, 105.7469));

        orders.add(new Order(4, "ORD004", 104, 7, 8, 1200.0, 40.0, 6,
                LocalDateTime.now().plusHours(8), 20.8449, 106.6881, 21.0285, 105.8542));

        orders.add(new Order(5, "ORD005", 105, 9, 10, 600.0, 25.0, 4,
                LocalDateTime.now().plusHours(24), 12.2585, 109.0526, 10.7769, 106.7009));

        return orders;
    }

    public static List<Order> generateFakeOrders() {
        List<Order> orders = new ArrayList<>();

        // Tạo 5 đơn hàng fake với thông tin chi tiết
        orders.add(new Order(1, "ORD001", 101, 1, 2, 1500.0, 45.0, 8,
                LocalDateTime.now().plusHours(12), 21.0285, 105.8542, 10.7769, 106.7009));

        orders.add(new Order(2, "ORD002", 102, 3, 4, 2200.0, 60.0, 9,
                LocalDateTime.now().plusHours(6), 10.7769, 106.7009, 16.4637, 107.5909));

        orders.add(new Order(3, "ORD003", 103, 5, 6, 800.0, 30.0, 5,
                LocalDateTime.now().plusHours(24), 16.4637, 107.5909, 10.0452, 105.7469));

        orders.add(new Order(4, "ORD004", 104, 7, 8, 1200.0, 40.0, 7,
                LocalDateTime.now().plusHours(8), 20.8449, 106.6881, 21.0285, 105.8542));

        orders.add(new Order(5, "ORD005", 105, 9, 10, 600.0, 25.0, 6,
                LocalDateTime.now().plusHours(18), 12.2585, 109.0526, 10.7769, 106.7009));

        return orders;
    }

    public static List<Vehicle> generateFakeVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        // Tạo 6 xe với capacity khác nhau
        vehicles.add(new Vehicle(1, "29A-12345", 2000.0, 60.0, 21.0285, 105.8542, "1")); // Xe nhỏ ở HN
        vehicles.add(new Vehicle(2, "51B-67890", 2500.0, 80.0, 10.7769, 106.7009, "1")); // Xe trung ở HCM
        vehicles.add(new Vehicle(3, "31C-11111", 3000.0, 100.0, 16.4637, 107.5909, "1")); // Xe lớn ở Huế
        vehicles.add(new Vehicle(4, "43D-22222", 2000.0, 70.0, 20.8449, 106.6881, "1")); // Xe nhỏ ở Hải Phòng
        vehicles.add(new Vehicle(5, "58E-33333", 3500.0, 120.0, 10.7769, 106.7009, "1")); // Xe rất lớn ở HCM
        vehicles.add(new Vehicle(6, "79F-44444", 1500.0, 50.0, 12.2585, 109.0526, "1")); // Xe rất nhỏ ở Nha Trang

        return vehicles;
    }

    public static List<Driver> generateFakeDrivers() {
        List<Driver> drivers = new ArrayList<>();

        // Tạo 8 tài xế fake
        drivers.add(new Driver(1, "DRV001", "Nguyễn Văn An", 1, 1, 1));
        drivers.add(new Driver(2, "DRV002", "Trần Văn Bình", 1, 1, 0));
        drivers.add(new Driver(3, "DRV003", "Lê Văn Cường", 2, 1, 2));
        drivers.add(new Driver(4, "DRV004", "Phạm Văn Dũng", 2, 1, 1));
        drivers.add(new Driver(5, "DRV005", "Hoàng Văn Em", 3, 1, 0));
        drivers.add(new Driver(6, "DRV006", "Vũ Văn Phú", 4, 1, 1));
        drivers.add(new Driver(7, "DRV007", "Đặng Văn Giang", 5, 1, 0));
        drivers.add(new Driver(8, "DRV008", "Bùi Văn Hưng", 6, 1, 2));

        return drivers;
    }

    public static List<VehicleDriverTeam> generateFakeTeams() {
        List<VehicleDriverTeam> teams = new ArrayList<>();

        // Mapping xe - tài xế (nhiều tài xế có thể lái cùng 1 xe)
        teams.add(new VehicleDriverTeam(1, 1)); // Xe 1 - Tài xế 1
        teams.add(new VehicleDriverTeam(1, 2)); // Xe 1 - Tài xế 2
        teams.add(new VehicleDriverTeam(2, 3)); // Xe 2 - Tài xế 3
        teams.add(new VehicleDriverTeam(2, 4)); // Xe 2 - Tài xế 4
        teams.add(new VehicleDriverTeam(3, 5)); // Xe 3 - Tài xế 5
        teams.add(new VehicleDriverTeam(4, 6)); // Xe 4 - Tài xế 6
        teams.add(new VehicleDriverTeam(5, 7)); // Xe 5 - Tài xế 7
        teams.add(new VehicleDriverTeam(6, 8)); // Xe 6 - Tài xế 8

        return teams;
    }
}
