package com.mwb.service.api;

import com.mwb.dao.model.AdminEmployee;
import com.mwb.dao.model.employee.Employee;

public interface IEmployeeService {

	public AdminEmployee login(AdminEmployee admin);

	public Employee getEmployeeById();

}
