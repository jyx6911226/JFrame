package com.jyx.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 测试类
 *
 * @author zhangqing
 * @date 2017年02月23日
 */
public class SchedulerTest1 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("测试job-schedulerTest1");
    }
}
