package com.bn.milk.model;

public class Vehicle {
    private int id;
    private String regNo;
    private double weight;
    private double volume;
    private double latitude;
    private double longitude;
    private String status;

    // Constructors
    public Vehicle() {}

    public Vehicle(int id, String regNo, double weight, double volume,
                   double latitude, double longitude, String status) {
        this.id = id;
        this.regNo = regNo;
        this.weight = weight;
        this.volume = volume;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getVolume() { return volume; }
    public void setVolume(double volume) { this.volume = volume; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}