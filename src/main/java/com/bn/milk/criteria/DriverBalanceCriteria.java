package com.bn.milk.criteria;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

import java.util.Map;

public class  DriverBalanceCriteria extends BaseCriteria {
    private Map<Integer, Integer> driverOrderCounts;

    public DriverBalanceCriteria(double weight, Map<Integer, Integer> driverOrderCounts) {
        super("Driver Balance", weight);
        this.driverOrderCounts = driverOrderCounts;
    }

    @Override
    public double calculate(Order order, Vehicle vehicle, Driver driver) {
        // Phân bổ đều cho tài xế (tài xế có ít đơn hơn sẽ được ưu tiên)
        int currentCount = driverOrderCounts.getOrDefault(driver.getId(), 0);
        int maxCount = driverOrderCounts.values().stream().mapToInt(Integer::intValue).max().orElse(1);

        // Normalize (0-1, tài xế có ít đơn hơn có điểm cao hơn)
        return Math.max(0, 1 - ((double) currentCount / maxCount));
    }
}