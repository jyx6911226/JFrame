package com.jyx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jyx.pojo.UserInfo;
import com.jyx.service.UserInfoService;
import com.jyx.util.i18n.I18nUtil;

/**
 * 用户注册控制器
 *
 * @author JYX
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private I18nUtil i18nUtil;
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping({"/init"})
    public ModelAndView init() {
        return new ModelAndView("/register");
    }

    /**
     * RedirectAttributes是重定向的参数容器
     * addFlashAttribute用于携带临时性的参数，比如提示信息
     */
    @RequestMapping({"/add"})
    public ModelAndView add(UserInfo userInfo, Model model, RedirectAttributes red_attrs) {
        try {
            userInfo.setState((byte) 1);
            //密码加密
            String password_cipherText = new Md5Hash(userInfo.getPassword(), userInfo.getCredentialsSalt(), 2).toHex();
            userInfo.setPassword(password_cipherText);
            userInfoService.save(userInfo);
            red_attrs.addFlashAttribute("res", 1);
            red_attrs.addFlashAttribute("msg", i18nUtil.getMessage("operation.success"));
        } catch (Exception e) {
            red_attrs.addFlashAttribute("res", 2);
            red_attrs.addFlashAttribute("msg", i18nUtil.getMessage("operation.failed"));
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/register/init");
    }

    @RequestMapping({"/validUsername"})
    @ResponseBody
    public Map<String, Boolean> validUsername(String username) {
        boolean valid = false;
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            UserInfo userInfo = userInfoService.findByUsername(username);
            if (userInfo == null) {
                valid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("valid", valid);
        return map;
    }
}
