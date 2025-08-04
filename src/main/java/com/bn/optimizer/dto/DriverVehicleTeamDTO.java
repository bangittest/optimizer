package com.bn.optimizer.dto;

public class DriverVehicleTeamDTO {
    private Integer vehicleId;
    private Integer driverId;

    public DriverVehicleTeamDTO(Integer vehicleId, Integer driverId) {
        this.vehicleId = vehicleId;
        this.driverId = driverId;
    }

    // Getters and Setters
    public Integer getVehicleId() { return vehicleId; }
    public void setVehicleId(Integer vehicleId) { this.vehicleId = vehicleId; }

    public Integer getDriverId() { return driverId; }
    public void setDriverId(Integer driverId) { this.driverId = driverId; }
}
