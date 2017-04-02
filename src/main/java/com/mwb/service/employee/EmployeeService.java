package com.mwb.service.employee;

import java.util.List;

import com.mwb.dao.filter.EmployeeFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.mapper.EmployeeMapper;
import com.mwb.dao.model.AdminEmployee;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.Group;
import com.mwb.service.employee.api.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;


    public Employee getEmployeeById(){
        return  employeeMapper.selectEmployeeById(1);
    }

    @Override
    public void createEmployee(Employee employee) {

    }

    @Override
    public void modifyEmployee(Employee employee) {

    }

    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }

    @Override
    public SearchResult<Employee> searchEmployeeByFilter(EmployeeFilter filter) {
        return null;
    }

    @Override
    public void createGroup(Group group) {

    }

    @Override
    public Group getGroupById(Integer id) {
        return null;
    }

    @Override
    public List<Group> getAllGroup() {
        return null;
    }
}
