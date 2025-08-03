package com.bn.milk.criteria;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

import java.time.LocalDateTime;

public class ETDTimeCriteria extends BaseCriteria {
    public ETDTimeCriteria(double weight) {
        super("ETD Time", weight);
    }

    @Override
    public double calculate(Order order, Vehicle vehicle, Driver driver) {
        // Tính độ phù hợp về thời gian giao hàng
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime etd = order.getEtdDate();

        if (etd.isBefore(now)) {
            return 0; // Đã quá hạn
        }

        long hoursDiff = java.time.Duration.between(now, etd).toHours();
        // Normalize (0-1, thời gian hợp lý là 1-48 giờ)
        return Math.max(0, Math.min(1, (48 - hoursDiff) / 48.0));
    }
}
