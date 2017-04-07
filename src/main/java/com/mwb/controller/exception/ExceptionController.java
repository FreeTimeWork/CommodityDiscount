package com.mwb.controller.exception;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常控制
 * 跳转页面
 */
//// TODO: 2017/4/7  
//@ControllerAdvice
public class ExceptionController {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler({Exception.class})
	public ModelAndView Exception(Exception ex){

		LOGGER.error("出错了异常",ex);

		ModelAndView mv = new ModelAndView("404");

		mv.addObject("exception", ex);
		return mv;
	}
	
}

