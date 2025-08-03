package com.bn.milk.repository;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;
import com.bn.milk.model.VehicleDriverTeam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private String connectionString;
    private String username;
    private String password;

    public DatabaseManager(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    public List<Order> getUnassignedOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = """
            SELECT o.id, o.order_code, o.customer_id, o.location_destination_id, 
                   o.location_arrival_id, o.weight, o.volume, o.precedence, o.ETD_date,
                   ld.latitude as dest_lat, ld.longitude as dest_lng,
                   la.latitude as arrival_lat, la.longitude as arrival_lng
            FROM orders o
            LEFT JOIN locations ld ON o.location_destination_id = ld.id
            LEFT JOIN locations la ON o.location_arrival_id = la.id
            WHERE o.vehicle_id IS NULL AND o.primary_driver_id IS NULL 
            AND o.del_flag = '0' AND o.status = 'PENDING'
            """;

        try (Connection conn = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderCode(rs.getString("order_code"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setLocationDestinationId(rs.getInt("location_destination_id"));
                order.setLocationArrivalId(rs.getInt("location_arrival_id"));
                order.setWeight(rs.getDouble("weight"));
                order.setVolume(rs.getDouble("volume"));
                order.setPrecedence(rs.getInt("precedence"));

                Timestamp etdTimestamp = rs.getTimestamp("ETD_date");
                if (etdTimestamp != null) {
                    order.setEtdDate(etdTimestamp.toLocalDateTime());
                }

                order.setLatitudeDest(rs.getDouble("dest_lat"));
                order.setLongitudeDest(rs.getDouble("dest_lng"));
                order.setLatitudeArrival(rs.getDouble("arrival_lat"));
                order.setLongitudeArrival(rs.getDouble("arrival_lng"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
            SELECT id, reg_no, weight, volume, latitude, longitude, status
            FROM vehicle
            WHERE status = '1' AND active = '1' AND del_flag = '0'
            """;

        try (Connection conn = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setRegNo(rs.getString("reg_no"));
                vehicle.setWeight(rs.getDouble("weight"));
                vehicle.setVolume(rs.getDouble("volume"));
                vehicle.setLatitude(rs.getDouble("latitude"));
                vehicle.setLongitude(rs.getDouble("longitude"));
                vehicle.setStatus(rs.getString("status"));

                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    public List<com.bn.milk.model.Driver> getAvailableDrivers() {
        List<com.bn.milk.model.Driver> drivers = new ArrayList<>();
        String sql = """
            SELECT d.id, d.code, d.full_name, d.vehicle_team_id, d.working_status,
                   COUNT(o.id) as current_orders_count
            FROM drivers d
            LEFT JOIN orders o ON d.id = o.primary_driver_id AND o.status IN ('ASSIGNED', 'IN_PROGRESS')
            WHERE d.active = '1' AND d.del_flag = '0' AND d.working_status = 1
            GROUP BY d.id, d.code, d.full_name, d.vehicle_team_id, d.working_status
            """;

        try (Connection conn = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                com.bn.milk.model.Driver driver = new Driver();
                driver.setId(rs.getInt("id"));
                driver.setCode(rs.getString("code"));
                driver.setFullName(rs.getString("full_name"));
                driver.setVehicleTeamId(rs.getInt("vehicle_team_id"));
                driver.setWorkingStatus(rs.getInt("working_status"));
                driver.setCurrentOrdersCount(rs.getInt("current_orders_count"));

                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drivers;
    }

    public List<VehicleDriverTeam> getVehicleDriverTeams() {
        List<VehicleDriverTeam> teams = new ArrayList<>();
        String sql = "SELECT driver_id, vehicle_team_id FROM driver_vehicle_team";

        try (Connection conn = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                teams.add(new VehicleDriverTeam(
                        rs.getInt("vehicle_team_id"),
                        rs.getInt("driver_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    public void updateOrderAssignment(int orderId, int vehicleId, int driverId) {
        String sql = "UPDATE orders SET vehicle_id = ?, primary_driver_id = ?, status = 'ASSIGNED' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicleId);
            stmt.setInt(2, driverId);
            stmt.setInt(3, orderId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
