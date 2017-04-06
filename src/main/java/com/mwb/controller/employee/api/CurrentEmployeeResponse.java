package com.mwb.controller.employee.api;

import com.mwb.controller.api.ServiceResponse;

/**
 * Created by fangchen.chai on 2017/4/6.
 */
public class CurrentEmployeeResponse extends ServiceResponse{
    private EmployeeVO employee;

    public EmployeeVO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeVO employee) {
        this.employee = employee;
    }
}
