package com.mwb.service.employee;

import com.mwb.controller.api.PagingResult;
import com.mwb.dao.filter.EmployeeFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.mapper.EmployeeMapper;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.Group;
import com.mwb.service.employee.api.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public boolean createEmployee(Employee employee) {
        boolean result = true;
        EmployeeFilter filter = new EmployeeFilter();
        filter.setMobile(employee.getMobile());
        int count = employeeMapper.countEmployeeByFilter(filter);
        if (count > 0) {
            result = false;
        } else {
            employeeMapper.insertEmployee(employee);
        }
        return result;
    }

    @Override
    public void modifyEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeMapper.selectAllEmployee();
    }

    @Override
    public SearchResult<Employee> searchEmployeeByFilter(EmployeeFilter filter) {
        SearchResult<Employee> result = new SearchResult<>();
        List<Employee> employees = employeeMapper.selectEmployeeByFilter(filter);

        result.setResult(employees);

        if (filter.isPaged() && filter.getPagingData() != null) {
            int recordNumber = employeeMapper.countEmployeeByFilter(filter);
            PagingResult pagingResult = new PagingResult(recordNumber, filter.getPagingData());
            result.setPagingResult(pagingResult);
            result.setPaged(true);
        }

        return result;
    }

    @Override
    public Employee getEmployeeByMobileAndPassword(String mobile, String password) {
        return employeeMapper.selectEmployeeByMobileAndPassword(mobile,password);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.selectEmployeeById(id);
    }

    @Override
    public void createGroup(Group group) {

        employeeMapper.insertGroup(group);

    }

    @Override
    public Group getGroupById(Integer id) {
        return employeeMapper.selectGroupById(id);
    }

    public Group getGroupByName(String name){
        return  employeeMapper.selectGroupByName(name);
    }

    @Override
    public List<Group> getAllGroup() {
        return employeeMapper.selectAllGroup();
    }

}
