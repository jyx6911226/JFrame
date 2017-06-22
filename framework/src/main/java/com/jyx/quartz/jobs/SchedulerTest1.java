package com.jyx.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 测试类
 *
 */
public class SchedulerTest1 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        String a = null;
//        System.out.println(a.length());
        //获得任务的运行参数
        String testkey2 = (String)jobExecutionContext.getMergedJobDataMap().get("testkey");
        System.out.println("测试job-schedulerTest1");
    }
}
