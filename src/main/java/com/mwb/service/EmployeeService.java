package com.mwb.service;

import com.mwb.dao.mapper.EmployeeMapper;
import com.mwb.dao.model.AdminEmployee;
import com.mwb.service.api.IEmployeeService;
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
}
