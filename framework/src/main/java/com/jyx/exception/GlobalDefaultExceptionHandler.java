package com.jyx.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常处理类
 * 根据异常类型自动跳转至错误页面
 *
 * @author jyx
 *         可以根据不同的异常类型进行处理
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    /**
     * 处理的异常类型
     */
    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletRequest req, Exception e) {
        // // If the exception is annotated with @ResponseStatus rethrow it and let
        // // the framework handle it - like the OrderNotFoundException example
        // // at the start of this post.
        // // AnnotationUtils is a Spring Framework utility class.
        // if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
        // throw e;
        //
        // // Otherwise setup and send the user to a default error-view.
        // ModelAndView mav = new ModelAndView();
        // mav.addObject("exception", e);
        // mav.addObject("url", req.getRequestURL());
        // mav.setViewName(DEFAULT_ERROR_VIEW);
        // return mav;
        //打印异常信息：
        //如果requestType能拿到值，并且值为XMLHttpRequest,表示客户端的请求为异步请求，那自然是ajax请求了，反之如果为null,则是普通的请求
        //String reqtype = req.getHeader("X-Requested-With");
        if (e.getClass().isInstance(NullPointerException.class)) {
            System.out.println("空指针异常");
        } else if (e.getClass().isInstance(NumberFormatException.class)) {
            System.out.println("数字转换异常");
        } else {
            System.out.println("未知异常");
        }
        /*
		* 返回json数据或者String数据：
		* 那么需要在方法上加上注解：@ResponseBody
		* 添加return即可。
		*/
		/*
		* 返回视图：
		* 定义一个ModelAndView即可，
		* 然后return;
		* 定义视图文件(比如：error.html,error.ftl,error.jsp);
		*
		*/
    }
} 