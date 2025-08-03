package com.bn.milk.criteria;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

public class DistanceCriteria extends BaseCriteria {
    public DistanceCriteria(double weight) {
        super("Distance", weight);
    }

    @Override
    public double calculate(Order order, Vehicle vehicle, Driver driver) {
        // Tính khoảng cách từ vị trí xe đến điểm đến của đơn hàng
        double distance = calculateDistance(
                vehicle.getLatitude(), vehicle.getLongitude(),
                order.getLatitudeDest(), order.getLongitudeDest()
        );
        // Normalize distance (0-1, càng gần càng tốt)
        return Math.max(0, 1 - (distance / 1000.0)); // Giả sử max distance = 1000km
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
