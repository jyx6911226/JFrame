package com.jyx.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

/**
 * 定时器配置
 *
 * @author zhangqing
 * @date 2017年04月13日
 */
@Configuration
@EnableScheduling
public class QuartzConfig {
	
    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        // 延时启动
        factory.setStartupDelay(20);
        // 加载quartz数据源配置
        //factory.setQuartzProperties(quartzProperties());
        // 自定义Job Factory，用于Spring注入
        factory.setJobFactory(jobFactory);

        return factory;
    }

    ///**
    // * 加载quartz数据源配置
    // *
    // * @return
    // * @throws IOException
    // */
    //@Bean
    //public Properties quartzProperties() throws IOException {
    //    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    //    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
    //    propertiesFactoryBean.afterPropertiesSet();
    //    return propertiesFactoryBean.getObject();
    //}
}
