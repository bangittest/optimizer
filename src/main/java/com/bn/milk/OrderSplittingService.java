package com.bn.milk;

import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

import java.util.*;

public class OrderSplittingService {
    private static int nextSubOrderId = 1000; // ID bắt đầu cho đơn con
    /**
     * Tách một đơn hàng lớn thành nhiều đơn con dựa trên capacity của xe
     */
    public static List<Order> splitOrder(Order parentOrder, List<Vehicle> availableVehicles) {
        List<Order> subOrders = new ArrayList<>();

        // Tính toán số đơn con cần tách dựa trên capacity xe lớn nhất
        double maxVehicleWeight = availableVehicles.stream()
                .mapToDouble(Vehicle::getWeight)
                .max()
                .orElse(1000.0);

        double maxVehicleVolume = availableVehicles.stream()
                .mapToDouble(Vehicle::getVolume)
                .max()
                .orElse(50.0);

        // Tính số đơn con cần thiết
        int weightSplits = (int) Math.ceil(parentOrder.getWeight() / maxVehicleWeight);
        int volumeSplits = (int) Math.ceil(parentOrder.getVolume() / maxVehicleVolume);
        int totalSplits = Math.max(weightSplits, volumeSplits);

        // Nếu không cần tách thì return đơn gốc
        if (totalSplits <= 1) {
            return Arrays.asList(parentOrder);
        }

        System.out.printf("Tách đơn %s thành %d đơn con%n", parentOrder.getOrderCode(), totalSplits);

        // Tách đơn
        double subWeight = parentOrder.getWeight() / totalSplits;
        double subVolume = parentOrder.getVolume() / totalSplits;
        List<Integer> subOrderIds = new ArrayList<>();

        for (int i = 1; i <= totalSplits; i++) {
            Order subOrder = new Order();
            subOrder.setId(nextSubOrderId++);
            subOrder.setOrderCode(parentOrder.getOrderCode());
            subOrder.setSubOrderCode(parentOrder.getOrderCode() + "-A" + i);
            subOrder.setCustomerId(parentOrder.getCustomerId());
            subOrder.setLocationDestinationId(parentOrder.getLocationDestinationId());
            subOrder.setLocationArrivalId(parentOrder.getLocationArrivalId());
            subOrder.setWeight(subWeight);
            subOrder.setVolume(subVolume);
            subOrder.setPrecedence(parentOrder.getPrecedence());
            subOrder.setEtdDate(parentOrder.getEtdDate());
            subOrder.setLatitudeDest(parentOrder.getLatitudeDest());
            subOrder.setLongitudeDest(parentOrder.getLongitudeDest());
            subOrder.setLatitudeArrival(parentOrder.getLatitudeArrival());
            subOrder.setLongitudeArrival(parentOrder.getLongitudeArrival());

            // Đánh dấu là đơn con
            subOrder.setSubOrder(true);
            subOrder.setParentOrderId(parentOrder.getId());
            subOrder.setSplitIndex(i);
            subOrder.setOriginalWeight(parentOrder.getWeight());
            subOrder.setOriginalVolume(parentOrder.getVolume());

            subOrders.add(subOrder);
            subOrderIds.add(subOrder.getId());
        }

        // Cập nhật đơn gốc
        parentOrder.setSubOrderIds(subOrderIds);

        return subOrders;
    }

    /**
     * Gộp các đơn con vào cùng một xe dựa trên tối ưu hóa
     */
    public static Map<Integer, List<Order>> consolidateOrders(List<Order> subOrders, List<Vehicle> vehicles) {
        Map<Integer, List<Order>> consolidation = new HashMap<>();

        // Sắp xếp đơn theo trọng lượng giảm dần (First Fit Decreasing)
        List<Order> sortedOrders = new ArrayList<>(subOrders);
        sortedOrders.sort((o1, o2) -> Double.compare(o2.getWeight(), o1.getWeight()));

        // Khởi tạo capacity còn lại của mỗi xe
        Map<Integer, Double> remainingWeight = new HashMap<>();
        Map<Integer, Double> remainingVolume = new HashMap<>();

        for (Vehicle vehicle : vehicles) {
            remainingWeight.put(vehicle.getId(), vehicle.getWeight());
            remainingVolume.put(vehicle.getId(), vehicle.getVolume());
            consolidation.put(vehicle.getId(), new ArrayList<>());
        }

        // Gán đơn vào xe (First Fit)
        for (Order order : sortedOrders) {
            boolean assigned = false;

            for (Vehicle vehicle : vehicles) {
                double remWeight = remainingWeight.get(vehicle.getId());
                double remVolume = remainingVolume.get(vehicle.getId());

                if (order.getWeight() <= remWeight && order.getVolume() <= remVolume) {
                    consolidation.get(vehicle.getId()).add(order);
                    remainingWeight.put(vehicle.getId(), remWeight - order.getWeight());
                    remainingVolume.put(vehicle.getId(), remVolume - order.getVolume());
                    assigned = true;
                    break;
                }
            }

            if (!assigned) {
                System.out.printf("Cảnh báo: Không thể gán đơn %s vào xe nào%n", order.getSubOrderCode());
            }
        }

        return consolidation;
    }
}
