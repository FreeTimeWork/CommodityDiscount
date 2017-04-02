package com.mwb.service.employee;

import com.mwb.dao.mapper.EmployeeMapper;
import com.mwb.dao.model.AdminEmployee;
import com.mwb.dao.model.employee.Employee;
import com.mwb.service.employee.api.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public AdminEmployee login(AdminEmployee adminEmployee) {
        return null;
    }

    public Employee getEmployeeById(){
        return  employeeMapper.selectEmployeeById(1);
    }
}
