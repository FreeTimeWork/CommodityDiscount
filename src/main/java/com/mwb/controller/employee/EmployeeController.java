package com.mwb.controller.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.mwb.controller.api.ContentType;
import com.mwb.controller.employee.api.EmployeeResponse;
import com.mwb.dao.model.AdminEmployee;
import com.mwb.dao.model.Log;
import com.mwb.dao.model.employee.Employee;
import com.mwb.service.api.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private IEmployeeService adminService;

    @RequestMapping("/login")
    public String Login(AdminEmployee adminEmployee, HttpServletRequest request) {
        LOG.info("Login into ");
//        adminEmployee.setPassword(Md5);
        AdminEmployee manager = adminService.login(adminEmployee);
        if (manager == null) {
            LOG.info("admin =null ");
            request.setAttribute("err", "err");
            return "manager/login";
        } else {
            LOG.info("admin =ok ");

            HttpSession session = request.getSession();
            session.setAttribute("admin", manager);
            return "redirect:/static/manager/index";
        }
    }
    @ResponseBody
    @RequestMapping(value = "/login1", produces = ContentType.APPLICATION_JSON_UTF8)
    public EmployeeResponse Login() {
        EmployeeResponse response = new EmployeeResponse();
        AdminEmployee adminEmployee1 = new AdminEmployee();
        adminEmployee1.setPassword("12");
        Employee employee = adminService.getEmployeeById();
        String str = JSONArray.toJSONString(employee);
        response.setObject(str);
        response.setAdminEmployee(adminEmployee1);
        return response;
    }

}
