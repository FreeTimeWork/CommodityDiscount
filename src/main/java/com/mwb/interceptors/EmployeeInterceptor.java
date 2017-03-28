package com.mwb.interceptors;

import com.mwb.dao.model.Employee;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有静态文件
 * 前台 user 拦截器
 */
public class EmployeeInterceptor implements HandlerInterceptor{
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmployeeInterceptor.class);
	//不允许访问页面
	public String[] allowUrls;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LOGGER.info("[user] preHandle" );
		String requestUrl = request.getRequestURI().replace(
				request.getContextPath(), "");
		LOGGER.info("URI=" + request.getRequestURI() + " requestUrl= " + requestUrl);
		if (null != allowUrls && allowUrls.length >= 1)
			for (String url : allowUrls) {
				if (requestUrl.equals(url)) {
					Employee user=(Employee)request.getSession().getAttribute("user");
					if(user!=null){
						LOGGER.info("user!=null allow uri{}",url);
						return true;
					}else {
						LOGGER.info("user==null no allow uri{}",url);
						response.sendRedirect(request.getContextPath()+"/static/getLogin");
						return false;
					}
				}
			}
		//允许用户访问页面+静态文件放行
		return  true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
