package com.bn.milk.criteria;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

public class VehicleLoadCriteria extends BaseCriteria {
    public VehicleLoadCriteria(double weight) {
        super("Vehicle Load", weight);
    }

    @Override
    public double calculate(Order order, Vehicle vehicle, Driver driver) {
        // Kiểm tra khả năng chở của xe
        double weightRatio = order.getWeight() / vehicle.getWeight();
        double volumeRatio = order.getVolume() / vehicle.getVolume();

        if (weightRatio > 1 || volumeRatio > 1) {
            return 0; // Không thể chở
        }

        // Ưu tiên xe có dư thừa khả năng chở vừa phải
        double utilizationScore = Math.max(weightRatio, volumeRatio);
        return Math.max(0, 1 - Math.abs(0.8 - utilizationScore)); // Tối ưu ở 80% capacity
    }
}
