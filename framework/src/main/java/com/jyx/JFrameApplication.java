package com.jyx;

import com.jyx.util.spring.SpringUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @SpringBootApplication 注解等价于以默认属性使用 @Configuration
 * @EnableAutoConfiguration 和 @ComponentScan
 */
@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan
@ServletComponentScan
/**
 * 加载工具类，用于获取Spring管理的类SpringUtil
 * 加载多数据源DynamicDataSourceRegister
 * */
@Import(value = {SpringUtil.class})
//@Import(value={SpringUtil.class})

/**
 * 可以使用：basePackageClasses={},basePackages={}
 * 添加需要扫描的包或类（只会扫描配置的包或类，JFrameApplication原有的包如果不配置的话也不会被扫描）
 * 如果不配置会默认扫描JFrameApplication原有的包
 * */
//@ComponentScan(basePackages={"com.jyx","com.jyx1"})
/**
 * 读取制定配置类，多个可用逗号隔开
 * {TestSettings.class,TestSettings2.class}
 * */
//@EnableConfigurationProperties({TestSettings.class})
/**
 * 当项目使用jsp作为视图时，只能使用war部署，不能使用jar（官方不支持），且只能使用tomcat
 * 使用WAR部署步骤：
 * 1.将项目的启动类Application.java继承SpringBootServletInitializer并重写configure方法
 *  @Override
 *  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
 *      return application.sources(Application.class);
 *  }
 * 2.在pom.xml文件中，project下面增加package标签
 * <packaging>war</packaging>
 * 3.还是在pom.xml文件中，dependencies下面添加 
 * <dependency>
 *       <groupId>org.springframework.boot</groupId>
 *      <artifactId>spring-boot-starter-tomcat</artifactId>
 *     <scope>provided</scope>
 * </dependency>
 * */
public class JFrameApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JFrameApplication.class);
    }

    /**
     * 这两种模式暂时无法同时使用。
     */
    //点对点模式，默认为queue
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }

    //群发模式spring.jms.pub-sub-domain=true
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("sample.topic");
    }

    /**
     * Spring Boot 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(JFrameApplication.class, args);
    }

}
