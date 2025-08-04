package com.bn.optimizer.dto;

public class VehicleDTO {
    private Integer id;
    private String regNo;
    private Double weight;
    private Double volume;
    private Double latitude;
    private Double longitude;
    private String status;

    public VehicleDTO() {
    }



    public VehicleDTO(Integer id, String regNo, Double weight, Double volume, Double latitude, Double longitude, String status) {
        this.id = id;
        this.regNo = regNo;
        this.weight = weight;
        this.volume = volume;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
