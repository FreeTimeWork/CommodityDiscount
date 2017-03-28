package com.mwb.controller.employee;

import com.mwb.controller.employee.api.EmployeeResponse;
import com.mwb.controller.api.ContentType;
import com.mwb.dao.model.Employee;
import com.mwb.dao.model.Log;
import com.mwb.service.api.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String Login(Employee employee, HttpServletRequest request) {
        LOG.info("Login into ");
//        employee.setPassword(Md5);
        Employee manager = adminService.login(employee);
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
        Employee employee1 = new Employee();
        employee1.setPassword("12");
        response.setEmployee(employee1);
        return response;
    }

}
