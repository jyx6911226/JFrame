package com.jyx.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.jyx.exception.IncorrectCaptchaException;
import com.jyx.util.captcha.CaptchaUsernamePasswordToken;

/**
 * 验证码过滤器
 * 此过滤器已经在shiro中配置，这里不需要再次配置拦截路径
 */
public class KaptchaFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    @Override
    /**
     * 登录验证 
     */
    protected boolean executeLogin(ServletRequest request,
                                   ServletResponse response) throws Exception {
        CaptchaUsernamePasswordToken token = createToken(request, response);
        try {  
            /*图形验证码验证*/
            doCaptchaValidate((HttpServletRequest) request, token);
            Subject subject = getSubject(request, response);
            subject.login(token);//正常验证  
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    // 验证码校验  
    protected void doCaptchaValidate(HttpServletRequest request,
                                     CaptchaUsernamePasswordToken token) {
        //session中的图形码字符串
        String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //比对  
        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new IncorrectCaptchaException();
        }
    }

    @Override
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request,
                                                       ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);

        return new CaptchaUsernamePasswordToken(username,
                password.toCharArray(), rememberMe, host, captcha);
    }


    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    //保存异常对象到request  
    @Override
    protected void setFailureAttribute(ServletRequest request,
                                       AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }
}
