package com.mwb.service.employee;

import java.util.List;

import com.mwb.controller.api.PagingRequest;
import com.mwb.controller.api.PagingResult;
import com.mwb.dao.filter.EmployeeFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.mapper.EmployeeMapper;
import com.mwb.dao.model.AdminEmployee;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.EmployeeStatus;
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
        employeeMapper.insertEmployee(employee);

    }

    @Override
    public void modifyEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public SearchResult<Employee> searchEmployeeByFilter(EmployeeFilter filter) {
        SearchResult<Employee> result = new SearchResult<>();
        List<Employee> employees = employeeMapper.selectEmployeeByFilter(filter);

        result.setResult(employees);

        if (filter.isPaged() && filter.getPagingData() != null) {
            int recordNumber = employeeMapper.countEmployeeByFiler(filter);
            PagingResult pagingResult = new PagingResult(recordNumber, filter.getPagingData());
            result.setPagingResult(pagingResult);
            result.setPaged(true);
        }

        return result;
    }

    @Override
    public void createGroup(Group group) {

        employeeMapper.insertGroup(group);

    }

    @Override
    public Group getGroupById(Integer id) {
        return employeeMapper.selectGroupById(id);
    }

    @Override
    public List<Group> getAllGroup() {
        return employeeMapper.selectAllGroup();
    }

}
