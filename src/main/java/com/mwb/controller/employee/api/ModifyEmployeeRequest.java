package com.mwb.controller.employee.api;

/**
 * Created by fangchen.chai on 2017/4/1.
 */
public class ModifyEmployeeRequest {

    private Integer employeeId;
    private String password;
    private Integer positionId;
    private boolean dismission;
    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public boolean isDismission() {
        return dismission;
    }

    public void setDismission(boolean dismission) {
        this.dismission = dismission;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}
