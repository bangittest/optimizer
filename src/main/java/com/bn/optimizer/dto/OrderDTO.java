package com.bn.optimizer.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private Integer id;
    private String orderCode;
    private Integer customerId;
    private Integer locationDestinationId;
    private Integer locationArrivalId;
    private Double weight;
    private Double volume;
    private Integer precedence; // Mức độ ưu tiên
    private LocalDateTime etdDate;
    private Double latitudeDest;
    private Double longitudeDest;
    private Double latitudeArrival;
    private Double longitudeArrival;

    public OrderDTO() {
    }

    public OrderDTO(Integer id, String orderCode, Integer customerId, Integer locationDestinationId, Integer locationArrivalId, Double weight, Double volume, Integer precedence, LocalDateTime etdDate, Double latitudeDest, Double longitudeDest, Double latitudeArrival, Double longitudeArrival) {
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
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getLocationDestinationId() {
        return locationDestinationId;
    }

    public void setLocationDestinationId(Integer locationDestinationId) {
        this.locationDestinationId = locationDestinationId;
    }

    public Integer getLocationArrivalId() {
        return locationArrivalId;
    }

    public void setLocationArrivalId(Integer locationArrivalId) {
        this.locationArrivalId = locationArrivalId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getPrecedence() {
        return precedence;
    }

    public void setPrecedence(Integer precedence) {
        this.precedence = precedence;
    }

    public LocalDateTime getEtdDate() {
        return etdDate;
    }

    public void setEtdDate(LocalDateTime etdDate) {
        this.etdDate = etdDate;
    }

    public Double getLatitudeDest() {
        return latitudeDest;
    }

    public void setLatitudeDest(Double latitudeDest) {
        this.latitudeDest = latitudeDest;
    }

    public Double getLongitudeDest() {
        return longitudeDest;
    }

    public void setLongitudeDest(Double longitudeDest) {
        this.longitudeDest = longitudeDest;
    }

    public Double getLatitudeArrival() {
        return latitudeArrival;
    }

    public void setLatitudeArrival(Double latitudeArrival) {
        this.latitudeArrival = latitudeArrival;
    }

    public Double getLongitudeArrival() {
        return longitudeArrival;
    }

    public void setLongitudeArrival(Double longitudeArrival) {
        this.longitudeArrival = longitudeArrival;
    }
}
