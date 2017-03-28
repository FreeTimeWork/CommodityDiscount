package com.mwb.controller;

import com.mwb.dao.model.Employee;
import com.mwb.dao.model.Log;
import com.mwb.service.api.IEmployeeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
