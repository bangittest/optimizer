package com.bn.milk.criteria;


import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleUtilizationCriteria extends BaseCriteria {
    private Map<Integer, Double> currentVehicleLoad;

    public VehicleUtilizationCriteria(double weight, Map<Integer, Double> currentVehicleLoad) {
        super("Vehicle Utilization", weight);
        this.currentVehicleLoad = currentVehicleLoad != null ? currentVehicleLoad : new HashMap<>();
    }

    @Override
    public double calculate(Order order, Vehicle vehicle, Driver driver) {
        double currentLoad = currentVehicleLoad.getOrDefault(vehicle.getId(), 0.0);
        double newLoad = currentLoad + order.getWeight();
        double utilizationRatio = newLoad / vehicle.getWeight();

        // Ưu tiên xe có utilization từ 70-90%
        if (utilizationRatio > 1.0) return 0; // Quá tải
        if (utilizationRatio < 0.3) return 0.3; // Tải quá ít
        if (utilizationRatio >= 0.7 && utilizationRatio <= 0.9) return 1.0; // Tối ưu

        return Math.max(0, 1 - Math.abs(0.8 - utilizationRatio));
    }
}
