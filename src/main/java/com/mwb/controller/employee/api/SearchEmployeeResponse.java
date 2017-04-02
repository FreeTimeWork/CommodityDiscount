package com.mwb.controller.employee.api;

import java.util.ArrayList;
import java.util.List;

import com.mwb.controller.api.PagingResponse;
import com.mwb.dao.model.employee.Employee;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by fangchen.chai on 2017/4/1.
 */
public class SearchEmployeeResponse extends PagingResponse {

    private static final long serialVersionUID = 1L;

    private List<EmployeeVO> employees;

    public static List<EmployeeVO> toVOs(List<Employee> employees){
        List<EmployeeVO> vos = new ArrayList<EmployeeVO>();
        if (CollectionUtils.isNotEmpty(employees)){
            for (Employee employee : employees){
                EmployeeVO vo = EmployeeVO.toVO(employee);
                vos.add(vo);
            }
        }
        return vos;
    }

    public List<EmployeeVO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeVO> employees) {
        this.employees = employees;
    }
}
