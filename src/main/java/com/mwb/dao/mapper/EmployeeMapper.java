package com.mwb.dao.mapper;

import com.mwb.dao.model.employee.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Fangchen.chai on 2017/3/29.
 */
public interface EmployeeMapper {

    public void insertEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public Employee selectEmployeeById(@Param("id") String id);
}
