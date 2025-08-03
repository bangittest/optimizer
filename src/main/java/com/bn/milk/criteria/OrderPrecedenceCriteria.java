package com.bn.milk.criteria;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

public class OrderPrecedenceCriteria extends BaseCriteria {
    public OrderPrecedenceCriteria(double weight) {
        super("Order Precedence", weight);
    }

    @Override
    public double calculate(Order order, Vehicle vehicle, Driver driver) {
        // Mức độ ưu tiên của đơn hàng (1-10, 10 là cao nhất)
        return Math.max(0, Math.min(1, order.getPrecedence() / 10.0));
    }
}
