package com.mwb.controller.employee.api;

/**
 * Created by fangchen.chai on 2017/4/9.
 */
public class ModifyPasswordRequest {
    private Integer employeeId;
    private String password;

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

}
