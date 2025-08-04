package com.bn.optimizer.dto;

import java.util.List;


public class DataDTO {
    private List<CriteriaDTO> criteria;
    private List<OrderDTO> orders;
    private List<VehicleDTO> vehicles;
    private List<DriverDTO> drivers;
    private List<DriverVehicleTeamDTO> driverVehicleTeam;

    public List<CriteriaDTO> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<CriteriaDTO> criteria) {
        this.criteria = criteria;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }

    public List<DriverDTO> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverDTO> drivers) {
        this.drivers = drivers;
    }

    public List<DriverVehicleTeamDTO> getDriverVehicleTeam() {
        return driverVehicleTeam;
    }

    public void setDriverVehicleTeam(List<DriverVehicleTeamDTO> driverVehicleTeam) {
        this.driverVehicleTeam = driverVehicleTeam;
    }
}

