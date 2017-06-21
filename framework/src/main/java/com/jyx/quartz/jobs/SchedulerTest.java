package com.jyx.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.jyx.service.UserInfoService;

import javax.annotation.Resource;

/**
 * 测试类
 *
 */
public class SchedulerTest implements Job {
    @Resource
    private UserInfoService userService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            System.out.println(userService.findEntityPage(null, null, null).getContent().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
