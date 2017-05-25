package com.jyx.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 服务启动执行
 * *
 */
@Component
/**
 * @Order用于定义启动执行的顺序
 * 数字越小越先执行
 * */
//@Order(value=2)
public class StartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        //System.out.println(">>>>>>>>>>>>>>>服务启动执行，预加载数据<<<<<<<<<<<<<");
    }
}