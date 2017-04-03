package com.mwb.controller.login;

import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.login.api.LoginRequest;
import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.model.employee.Employee;
import com.mwb.service.employee.api.IEmployeeService;
import com.mwb.util.MD5Tools;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by fangchen.chai on 2017/4/3.
 */
@Controller
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    public ServiceResponse login(LoginRequest request) {
        ServiceResponse response = new ServiceResponse();
        if (StringUtils.isNotEmpty(request.getMobile()) && StringUtils.isNotEmpty(request.getPassword())) {

            Employee employee = employeeService.getEmployeeByMobileAndPassword(request.getMobile(), MD5Tools.MD5(request.getPassword()));

            if (employee != null) {
                ApplicationContextUtils.getSession().setAttribute("employee", employee);
            } else {
                response.setMessage("用户名或密码错误");
            }
        } else {
            response.setMessage("账户或密码为空！");
        }

        return response;
    }
}
