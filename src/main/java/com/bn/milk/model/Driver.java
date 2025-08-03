package com.bn.milk.model;

public class Driver {
    private int id;
    private String code;
    private String fullName;
    private int vehicleTeamId;
    private int workingStatus;
    private int currentOrdersCount;

    // Constructors
    public Driver() {}

    public Driver(int id, String code, String fullName, int vehicleTeamId,
                  int workingStatus, int currentOrdersCount) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.vehicleTeamId = vehicleTeamId;
        this.workingStatus = workingStatus;
        this.currentOrdersCount = currentOrdersCount;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getVehicleTeamId() { return vehicleTeamId; }
    public void setVehicleTeamId(int vehicleTeamId) { this.vehicleTeamId = vehicleTeamId; }

    public int getWorkingStatus() { return workingStatus; }
    public void setWorkingStatus(int workingStatus) { this.workingStatus = workingStatus; }

    public int getCurrentOrdersCount() { return currentOrdersCount; }
    public void setCurrentOrdersCount(int currentOrdersCount) { this.currentOrdersCount = currentOrdersCount; }
}
