package com.jyx.controller;

import com.jyx.util.i18n.I18nUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 登录模块控制器
 *
 * @author JYX
 */
@Controller
public class LoginController {
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String tologin(HttpServletRequest request, Map<String, Object> map) {

        return "/login";
    }

    // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {

        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "";

        if (exception != null) {
            if (UnknownAccountException.class.isInstance(exception)) {
                msg = i18nUtil.getMessage("login.account_not_exist");
            } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                msg = i18nUtil.getMessage("login.password_error");
            } else {
                msg = i18nUtil.getMessage("Unknown error");
            }
            map.put("msg", msg);
            return "/login";
        }
        //如果已经登录，直接跳转主页面
        return "/index";
    }
}
