package com.bn.optimizer.dto;

public class DriverDTO {

    private Integer id;
    private String code;
    private String fullName;
    private Integer vehicleTeamId;
    private Integer workingStatus;
    private Integer currentOrdersCount;

    public DriverDTO() {
    }

    public DriverDTO(Integer id, String code, String fullName, Integer vehicleTeamId, Integer workingStatus, Integer currentOrdersCount) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.vehicleTeamId = vehicleTeamId;
        this.workingStatus = workingStatus;
        this.currentOrdersCount = currentOrdersCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getVehicleTeamId() {
        return vehicleTeamId;
    }

    public void setVehicleTeamId(Integer vehicleTeamId) {
        this.vehicleTeamId = vehicleTeamId;
    }

    public Integer getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(Integer workingStatus) {
        this.workingStatus = workingStatus;
    }

    public Integer getCurrentOrdersCount() {
        return currentOrdersCount;
    }

    public void setCurrentOrdersCount(Integer currentOrdersCount) {
        this.currentOrdersCount = currentOrdersCount;
    }
}
