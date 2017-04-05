package com.mwb.service.employee.api;

import java.util.List;

import com.mwb.dao.filter.EmployeeFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.Group;

public interface IEmployeeService {

	public void createEmployee(Employee employee);

	public void modifyEmployee(Employee employee);

	public List<Employee> getAllEmployee();

	public SearchResult<Employee> searchEmployeeByFilter(EmployeeFilter filter);

	public Employee getEmployeeByMobileAndPassword(String mobile, String password);

	public void createGroup(Group group);

	public Group getGroupById(Integer id);

	public List<Group> getAllGroup();


}
