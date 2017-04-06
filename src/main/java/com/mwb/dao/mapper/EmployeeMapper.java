package com.mwb.dao.mapper;

import java.util.List;

import com.mwb.dao.filter.EmployeeFilter;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.Group;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Fangchen.chai on 2017/3/29.
 */
public interface EmployeeMapper {

    public List<Employee> selectEmployeeByFilter(@Param("filter") EmployeeFilter filter);

    public List<Employee> selectAllEmployee();

    public int countEmployeeByFilter(@Param("filter") EmployeeFilter filter);

    public void insertEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public Employee selectEmployeeById(@Param("id") Integer id);

    public Employee selectEmployeeByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    // group
    public void insertGroup(Group group);

    public List<Group> selectAllGroup();

    public Group selectGroupById(@Param("id") Integer id);

}
