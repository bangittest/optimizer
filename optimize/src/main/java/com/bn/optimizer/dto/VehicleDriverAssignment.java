package com.bn.optimizer.dto;

public class VehicleDriverAssignment {
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
