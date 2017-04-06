package com.mwb.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.model.employee.Employee;
import com.mwb.service.employee.api.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fangchen.chai on 2017/4/5.
 */
public class EmployeeInterceptor implements HandlerInterceptor {

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        Employee employee =(Employee)request.getSession().getAttribute("employee");
        employee = employeeService.getAllEmployee().get(0);
        ApplicationContextUtils.getSession().setAttribute("employee", employee);

//        if (employee != null){
//            return true;
//        } else {
//            response.sendRedirect(request.getContextPath()+"/index.html");
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
