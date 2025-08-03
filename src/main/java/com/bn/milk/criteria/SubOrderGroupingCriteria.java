package com.bn.milk.criteria;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

public class SubOrderGroupingCriteria extends BaseCriteria {
    public SubOrderGroupingCriteria(double weight) {
        super("Sub Order Grouping", weight);
    }

    @Override
    public double calculate(Order order, Vehicle vehicle, Driver driver) {
        // Ưu tiên gộp các đơn con của cùng một đơn gốc vào cùng xe
        if (!order.isSubOrder()) {
            return 0.5; // Đơn gốc có điểm trung bình
        }

        // Kiểm tra xem xe này đã có đơn con khác của cùng đơn gốc chưa
        // (Trong thực tế, cần truy vấn database hoặc maintain state)
        // Ở đây giả sử điểm cao nếu có thể gộp được
        return 0.8;
    }
}
