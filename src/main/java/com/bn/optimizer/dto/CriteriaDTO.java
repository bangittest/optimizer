package com.bn.optimizer.dto;

import com.bn.optimizer.model.Driver;
import com.bn.optimizer.model.Order;
import com.bn.optimizer.model.Vehicle;

import java.util.Map;

public class CriteriaDTO {
    private String name;
    private double weight;

    private Map<Integer, Integer> driverOrderCounts;

    private Map<Integer, Double> currentVehicleLoad;

    public double calculate(OrderDTO order, VehicleDTO vehicle, DriverDTO driver) {
        return 0;
    }

    // Getters và Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Map<Integer, Integer> getDriverOrderCounts() {
        return driverOrderCounts;
    }

    public void setDriverOrderCounts(Map<Integer, Integer> driverOrderCounts) {
        this.driverOrderCounts = driverOrderCounts;
    }

    public Map<Integer, Double> getCurrentVehicleLoad() {
        return currentVehicleLoad;
    }

    public void setCurrentVehicleLoad(Map<Integer, Double> currentVehicleLoad) {
        this.currentVehicleLoad = currentVehicleLoad;
    }
}
