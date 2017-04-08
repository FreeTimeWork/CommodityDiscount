package com.mwb.controller.login;

import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.login.api.LoginRequest;
import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.EmployeeStatus;
import com.mwb.service.employee.api.IEmployeeService;
import com.mwb.util.MD5Tools;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fangchen.chai on 2017/4/3.
 */
@Controller
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value = "/login")
    public ServiceResponse login(LoginRequest request) {
        ServiceResponse response = new ServiceResponse();
        if (StringUtils.isNotEmpty(request.getMobile()) && StringUtils.isNotEmpty(request.getPassword())) {

            Employee employee = employeeService.getEmployeeByMobileAndPassword(request.getMobile(), MD5Tools.MD5(request.getPassword()));

            if (employee != null) {
                if (employee.getStatus().equals(EmployeeStatus.IN_POSITION)) {
                    ApplicationContextUtils.getSession().setAttribute("employee", employee);
                } else {
                    response.setMessage("用户已离职");
                }
            } else {
                response.setMessage("用户名或密码错误");
            }
        } else {
            response.setMessage("账户或密码为空！");
        }

        return response;
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        ApplicationContextUtils.getSession().removeAttribute("employee");
        return "login";
    }

}
