package com.jyx.runner;

import com.jyx.pojo.SysScheduler;
import com.jyx.service.SysSchedulerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务启动执行
 * 启动所有运行状态的系统任务
 * *
 */
@Component
/**
 * @Order用于定义启动执行的顺序
 * 数字越小越先执行
 * */
@Order(value=10)
public class SchedulerRunner implements CommandLineRunner {

    @Resource
    private SysSchedulerService sysSchedulerService;

    @Override
    public void run(String... args) throws Exception {
        List<SysScheduler> sysSchedulerList = sysSchedulerService.findByStartFlag(true);
        for(SysScheduler sysScheduler : sysSchedulerList){
            try {
                sysSchedulerService.deploySysScheduler(sysScheduler);
                System.out.println("定时任务"+sysScheduler.getName()+"启动成功");
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("定时任务"+sysScheduler.getName()+"启动失败");
            }
        }
    }
}