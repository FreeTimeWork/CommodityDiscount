package com.mwb.controller.employee.api;

import com.mwb.dao.model.AdminEmployee;

/**
 * Created by MengWeiBo on 2017-03-28
 */
public class EmployeeResponse {
    private AdminEmployee adminEmployee;

    public AdminEmployee getAdminEmployee() {
        return adminEmployee;
    }

    public void setAdminEmployee(AdminEmployee adminEmployee) {
        this.adminEmployee = adminEmployee;
    }
}
