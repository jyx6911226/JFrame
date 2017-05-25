package com.jyx.config;

import java.util.Locale;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.google.code.kaptcha.servlet.KaptchaServlet;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
    /**
     * 添加总定义静态资源目录
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/WEB-INF/jsp/**").addResourceLocations("classpath:/WEB-INF/jsp");
        //添加外部目录
        //registry.addResourceHandler("/api_files/**").addResourceLocations("file:D:/data/api_files");
        super.addResourceHandlers(registry);
    }

    /**
     * 默认语言设置（简体中文）
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return slr;
    }

    /**
     * 多语言拦截器
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * 验证码配置
     */
    /*@Value("${kaptcha.border}") 
    private String kborder;*/

    @Value("${kaptcha.session.key}")
    private String skey;

    @Value("${kaptcha.textproducer.font.color}")
    private String fcolor;

    @Value("${kaptcha.textproducer.font.size}")
    private String fsize;

    @Value("${kaptcha.obscurificator.impl}")
    private String obscurificator;

    @Value("${kaptcha.noise.impl}")
    private String noise;

    @Value("${kaptcha.image.width}")
    private String width;

    @Value("${kaptcha.image.height}")
    private String height;

    @Value("${kaptcha.textproducer.char.length}")
    private String clength;

    @Value("${kaptcha.textproducer.char.space}")
    private String cspace;

    @Value("${kaptcha.background.clear.from}")
    private String from;

    @Value("${kaptcha.background.clear.to}")
    private String to;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() throws ServletException {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/images/kaptcha.jpg");
        servlet.addInitParameter("kaptcha.border", "no"/*kborder*/);//无边框  
        servlet.addInitParameter("kaptcha.session.key", skey);//session key  
        servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor);
        servlet.addInitParameter("kaptcha.textproducer.font.size", fsize);
        servlet.addInitParameter("kaptcha.obscurificator.impl", obscurificator);
        servlet.addInitParameter("kaptcha.noise.impl", noise);
        servlet.addInitParameter("kaptcha.image.width", width);
        servlet.addInitParameter("kaptcha.image.height", height);
        servlet.addInitParameter("kaptcha.textproducer.char.length", clength);
        servlet.addInitParameter("kaptcha.textproducer.char.space", cspace);
        servlet.addInitParameter("kaptcha.background.clear.from", from); //和登录框背景颜色一致   
        servlet.addInitParameter("kaptcha.background.clear.to", to);
        return servlet;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        super.addInterceptors(registry);
    }
//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*"); // 1
//        corsConfiguration.addAllowedHeader("*"); // 2
//        corsConfiguration.addAllowedMethod("*"); // 3
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 4
//        return new CorsFilter(source);
//    }
}




