package com.jyx.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页index控制器
 *
 * @author JYX
 */
@Controller
public class IndexController {
	
    @RequestMapping({"/", "/index"})
    public String toindex(HttpServletRequest request, Map<String, Object> map) {
        return "/index";
    }

    //欢迎页
    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, Map<String, Object> map) {

        return "/welcome";
    }
}
