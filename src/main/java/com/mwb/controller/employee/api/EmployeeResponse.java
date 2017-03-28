package com.mwb.controller.employee.api;

import com.mwb.dao.model.Employee;

/**
 * Created by MengWeiBo on 2017-03-28
 */
public class EmployeeResponse {
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
