package com.mwb.controller.employee;

import java.util.Date;
import java.util.List;

import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.employee.api.*;
import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.filter.EmployeeFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.comm.Log;
import com.mwb.dao.model.comm.PagingData;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.EmployeeStatus;
import com.mwb.dao.model.employee.Gender;
import com.mwb.dao.model.employee.Group;
import com.mwb.dao.model.position.Position;
import com.mwb.service.employee.api.IEmployeeService;
import com.mwb.util.MD5Tools;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台
 * 管理员
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private static final Log LOG = Log.getLog(EmployeeController.class);

    @Autowired
    private IEmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value = "/currentEmployee")
    public ServiceResponse getCurrentEmployee(){
        Employee employee = (Employee)ApplicationContextUtils.getSession().getAttribute("employee");
        CurrentEmployeeResponse response = new CurrentEmployeeResponse();
        if (employee != null){
            response.setEmployee(EmployeeVO.toVO(employee));
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/create")
    public ServiceResponse createEmployee(@RequestBody CreateEmployeeRequest request) {

        Employee employee = new Employee();
        employee.setFullName(request.getFullName());
        employee.setCreateTime(new Date());
        employee.setGender(Gender.fromCode(request.getGenderCode()));
        employee.setMobile(request.getMobile());
        employee.setPassword(MD5Tools.MD5(request.getPassword()));
        employee.setPosition(new Position(request.getPositionId()));
        employee.setStatus(EmployeeStatus.IN_POSITION);

        employeeService.createEmployee(employee);

        return new ServiceResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/group/verify")
    public Boolean verifyGroupLeader(Integer groupId) {
        Group group = employeeService.getGroupById(groupId);

        if (group != null && group.getEmployeeId() != null) {
            return true;
        }
        return false;
    }

    //    修改密码，分组，升级，离职
    @ResponseBody
    @RequestMapping(value = "/modify")
    public ServiceResponse modifyEmployee(@RequestBody ModifyEmployeeRequest request) {

        Employee employee = new Employee();
        employee.setId(request.getEmployeeId());
        //密码加密
        employee.setPassword(MD5Tools.MD5(request.getPassword()));
        employee.setPosition(new Position(request.getPositionId()));
        employee.setGroup(new Group(request.getGroupId()));
        if (request.isDismission()){
            employee.setStatus(EmployeeStatus.OUT_OF_POSITION);
        }

        employeeService.modifyEmployee(employee);

        return new ServiceResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public ServiceResponse searchEmployee(SearchEmployeeRequest request) {

        EmployeeFilter filter = new EmployeeFilter();
        filter.setFullName(request.getFullName());
        filter.setGroupId(request.getGroupId());
        filter.setPositionId(request.getPositionId());
        filter.setPaged(true);
        filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));

        SearchResult<Employee> result = employeeService.searchEmployeeByFilter(filter);
        SearchEmployeeResponse response = new SearchEmployeeResponse();
        response.setEmployees(SearchEmployeeResponse.toVOs(result.getResult()));
        response.setPagingResult(result.getPagingResult());
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/group/create")
    public ServiceResponse createGroup(@RequestBody CreateGroupRequest request) {

        if (StringUtils.isEmpty(request.getName())) {
            return new ServiceResponse();
        }

        Group group = new Group();
        group.setName(request.getName());
        employeeService.createGroup(group);

        return new ServiceResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/group/allGroup")
    public ServiceResponse getAllGroup() {

        List<Group> groups = employeeService.getAllGroup();

        SearchGroupResponse response = new SearchGroupResponse();
        response.setGroups(GroupVO.toVOs(groups));
        return response;
    }
}
