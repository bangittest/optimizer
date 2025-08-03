package com.bn.milk.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private String orderCode;
    private int customerId;
    private int locationDestinationId;
    private int locationArrivalId;
    private double weight;
    private double volume;
    private int precedence; // Mức độ ưu tiên
    private LocalDateTime etdDate;
    private double latitudeDest;
    private double longitudeDest;
    private double latitudeArrival;
    private double longitudeArrival;

    // Các thuộc tính cho việc tách/gộp đơn
    private Integer parentOrderId;
    private String subOrderCode;
    private boolean isSubOrder;
    private double originalWeight;
    private double originalVolume;
    private int splitIndex;
    private List<Integer> subOrderIds;

    // Constructors
    public Order() {
        this.subOrderIds = new ArrayList<>();
        this.isSubOrder = false;
    }

    public Order(int id, String orderCode, int customerId, int locationDestinationId,
                 int locationArrivalId, double weight, double volume, int precedence,
                 LocalDateTime etdDate, double latitudeDest, double longitudeDest,
                 double latitudeArrival, double longitudeArrival) {
        this.id = id;
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.locationDestinationId = locationDestinationId;
        this.locationArrivalId = locationArrivalId;
        this.weight = weight;
        this.volume = volume;
        this.precedence = precedence;
        this.etdDate = etdDate;
        this.latitudeDest = latitudeDest;
        this.longitudeDest = longitudeDest;
        this.latitudeArrival = latitudeArrival;
        this.longitudeArrival = longitudeArrival;
        this.subOrderIds = new ArrayList<>();
        this.isSubOrder = false;
        this.originalWeight = weight;
        this.originalVolume = volume;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOrderCode() { return orderCode; }
    public void setOrderCode(String orderCode) { this.orderCode = orderCode; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getLocationDestinationId() { return locationDestinationId; }
    public void setLocationDestinationId(int locationDestinationId) { this.locationDestinationId = locationDestinationId; }

    public int getLocationArrivalId() { return locationArrivalId; }
    public void setLocationArrivalId(int locationArrivalId) { this.locationArrivalId = locationArrivalId; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getVolume() { return volume; }
    public void setVolume(double volume) { this.volume = volume; }

    public int getPrecedence() { return precedence; }
    public void setPrecedence(int precedence) { this.precedence = precedence; }

    public LocalDateTime getEtdDate() { return etdDate; }
    public void setEtdDate(LocalDateTime etdDate) { this.etdDate = etdDate; }

    public double getLatitudeDest() { return latitudeDest; }
    public void setLatitudeDest(double latitudeDest) { this.latitudeDest = latitudeDest; }

    public double getLongitudeDest() { return longitudeDest; }
    public void setLongitudeDest(double longitudeDest) { this.longitudeDest = longitudeDest; }

    public double getLatitudeArrival() { return latitudeArrival; }
    public void setLatitudeArrival(double latitudeArrival) { this.latitudeArrival = latitudeArrival; }

    public double getLongitudeArrival() { return longitudeArrival; }
    public void setLongitudeArrival(double longitudeArrival) { this.longitudeArrival = longitudeArrival; }

    // Getters/Setters cho việc tách/gộp đơn
    public Integer getParentOrderId() { return parentOrderId; }
    public void setParentOrderId(Integer parentOrderId) { this.parentOrderId = parentOrderId; }

    public String getSubOrderCode() { return subOrderCode; }
    public void setSubOrderCode(String subOrderCode) { this.subOrderCode = subOrderCode; }

    public boolean isSubOrder() { return isSubOrder; }
    public void setSubOrder(boolean subOrder) { isSubOrder = subOrder; }

    public double getOriginalWeight() { return originalWeight; }
    public void setOriginalWeight(double originalWeight) { this.originalWeight = originalWeight; }

    public double getOriginalVolume() { return originalVolume; }
    public void setOriginalVolume(double originalVolume) { this.originalVolume = originalVolume; }

    public int getSplitIndex() { return splitIndex; }
    public void setSplitIndex(int splitIndex) { this.splitIndex = splitIndex; }

    public List<Integer> getSubOrderIds() { return subOrderIds; }
    public void setSubOrderIds(List<Integer> subOrderIds) { this.subOrderIds = subOrderIds; }
}
