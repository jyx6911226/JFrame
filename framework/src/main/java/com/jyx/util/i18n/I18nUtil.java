package com.jyx.util.i18n;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 多语言工具类
 * 用于获取多语言信息
 */
@Component
public class I18nUtil {
    @Resource
    private MessageSource messageSource;

    /**
     * @param code ：对应messages配置的key.
     * @return
     */
    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(code, null, locale);
        return msg;
    }
}
