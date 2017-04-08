package com.mwb.service.employee;

import com.mwb.controller.api.PagingResult;
import com.mwb.dao.filter.EmployeeFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.mapper.EmployeeMapper;
import com.mwb.dao.model.comm.BooleanResult;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.Group;
import com.mwb.dao.model.position.Position;
import com.mwb.service.employee.api.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public BooleanResult createEmployee(Employee employee) {
        BooleanResult result = new BooleanResult();
        EmployeeFilter filter = new EmployeeFilter();
        filter.setMobile(employee.getMobile());
        int count = employeeMapper.countEmployeeByFilter(filter);
        if (count > 0) {
            result.setResult(false);
        } else {
            employeeMapper.insertEmployee(employee);

            if (employee.getGroup() != null && employee.getGroup().getEmployeeName() != null) {
                Group group = employee.getGroup();
                group.setEmployeeId(employee.getId());
                //老组长降级
                if (group.getId() != null) {
                    demoteEmployee(group.getId());
                }
                //该人成新组长
                employeeMapper.updateGroup(group);
            }
        }
        return result;
    }

    private void demoteEmployee(int groupId) {
        //老组长降级
        Group oldGroup = employeeMapper.selectGroupById(groupId);
        Employee oldEmployee = new Employee();
        if (oldGroup.getEmployeeId() != null) {
            oldEmployee.setId(oldGroup.getEmployeeId());
            oldEmployee.setPosition(new Position(2));
            employeeMapper.updateEmployee(oldEmployee);
        }
    }
    @Override
    public void modifyEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);

        if (employee.getGroup() != null) {
            //老组长降级
            if (employee.getGroup().getId() != null) {
                demoteEmployee(employee.getGroup().getId());
            }
            //修改组长
            employeeMapper.updateGroup(employee.getGroup());
        }
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
