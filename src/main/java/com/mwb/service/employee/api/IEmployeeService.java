package com.mwb.service.employee.api;

import java.util.List;

import com.mwb.dao.filter.EmployeeFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.comm.BooleanResult;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.Group;

public interface IEmployeeService {

	public BooleanResult createEmployee(Employee employee);

	public void modifyEmployee(Employee employee);

	public List<Employee> getAllEmployee();

	public SearchResult<Employee> searchEmployeeByFilter(EmployeeFilter filter);

	public Employee getEmployeeByMobileAndPassword(String mobile, String password);

	public Employee getEmployeeById(Integer id);

	public void createGroup(Group group);

	public Group getGroupById(Integer id);

	public Group getGroupByName(String name);

	public List<Group> getAllGroup();


}
