package com.bn.milk.model;

public class VehicleDriverTeam {
    private int vehicleId;
    private int driverId;

    public VehicleDriverTeam(int vehicleId, int driverId) {
        this.vehicleId = vehicleId;
        this.driverId = driverId;
    }

    // Getters and Setters
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }
}
