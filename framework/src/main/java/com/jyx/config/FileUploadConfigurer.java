package com.jyx.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfigurer {
    /**
     * 配置文件上传路径
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
        factory.setMaxFileSize("128MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("512MB");
        //Sets the directory location where files will be stored.
        //factory.setLocation("文件上传路径");
        return factory.createMultipartConfig();
    }
}
