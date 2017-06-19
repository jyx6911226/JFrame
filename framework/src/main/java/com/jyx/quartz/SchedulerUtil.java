package com.jyx.quartz;

import com.alibaba.fastjson.JSON;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @Description: 定时任务管理类 ,定时任务增、删、改、暂停、恢复等。 单例
 */
@Component
public class SchedulerUtil{

	private Logger log = Logger.getLogger(SchedulerUtil.class);

	@Resource
	private Scheduler scheduler;

	private static final String JOB_GROUP_NAME = "JOBGROUP_NAME";

	private static final String TRIGGER_GROUP_NAME = "TRIGGERGROUP_NAME";


	/**
	 * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName
	 *            任务名
	 * @param jobClass
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * 
	 */
	public void addJob(String jobName, Class<? extends Job> jobClass, String time, JobDataMap jobDataMap) {
		addJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, jobClass, time, jobDataMap);
	}

	/**
	 * @Description: 添加一个定时任务
	 * 
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            任务
	 * @param cronExpression
	 *            时间设置，参考quartz说明文档
	 */
	public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass, String cronExpression,
			JobDataMap jobDataMap) {
		JobDetail jobDetail = JobBuilder.newJob(jobClass).usingJobData(jobDataMap == null ? new JobDataMap() : jobDataMap)// 判空
				.withIdentity(jobName, jobGroupName).build();// 任务名，任务组，任务执行类
		// 触发器
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)// 触发器名,触发器组
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();// 触发器时间设定
		addJob(jobDetail, trigger);
	}

	public void addJob(JobDetail jobDetail, Trigger trigger) {
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param cron
	 *
	 */
	public void modifyJobTime(String jobName, String cron, JobDataMap jobDataMap) {
		modifyJobTime(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, cron, jobDataMap);
	}

	/**
	 * @Description: 修改一个任务的触发时间
	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param cron
	 */
	public void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron, JobDataMap jobDataMap) {
		try {
			TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {// 不存在或定时策略一样
				return;
			}
			JobKey jobKey = new JobKey(jobName, jobGroupName);
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			jobDetail.getJobDataMap().putAll(jobDataMap == null ? new JobDataMap() : jobDataMap);
			trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
			removeJob(jobKey, triggerKey);// 删除
			addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobDetail.getJobClass(), cron, jobDataMap);// 添加

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isExist(String jobName, String jobGroup) {
		return isExist(new JobKey(jobName, jobGroup));
	}

	/**
	 * 判断任务是否存在
	 *
	 */
	public boolean isExist(JobKey jobKey) {
		try {
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			return jobDetail != null;
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public boolean isExist(String jobName) {
		return isExist(jobName, JOB_GROUP_NAME);
	}

	/**
	 * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * 
	 */
	public void removeJob(String jobName) {
		removeJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME);
	}

	public void removeJob(JobKey jobKey, TriggerKey triggerKey) {
		try {
			scheduler.unscheduleJob(triggerKey);
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		} // 移除触发器
	}

	/**
	 * @Description: 移除一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * 
	 */
	public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		removeJob(new JobKey(jobName, jobGroupName), new TriggerKey(triggerName, triggerGroupName));

	}

	/**
	 * 恢复一个job
	 *
	 */
	public void resumeJob(String jobName) {
		resumeJob(jobName, JOB_GROUP_NAME);
	}

	/**
	 * 恢复一个job
	 *
	 * @param jobName
	 * @param jobGroup
	 */
	public void resumeJob(String jobName, String jobGroup) {
		resumeJob(new JobKey(jobName, jobGroup));
	}

	/**
	 * 恢复一个job
	 *
	 * @param jobKey
	 */
	public void resumeJob(JobKey jobKey) {
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 暂停一个任务
	 *
	 * @param jobName
	 */
	public void pauseJob(String jobName) {
		pauseJob(jobName, JOB_GROUP_NAME);
	}

	/**
	 * 暂停一个任务
	 *
	 * @param jobName
	 * @param jobGroup
	 */
	public void pauseJob(String jobName, String jobGroup) {
		pauseJob(new JobKey(jobName, jobGroup));
	}

	/**
	 * 暂停一个任务
	 *
	 * @param jobKey
	 */
	public void pauseJob(JobKey jobKey) {
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:启动定时器
	 * @Title: QuartzManager.java
	 * @Copyright: Copyright (c) 2014
	 */
	public void startJobs() {
		try {
			if (!scheduler.isStarted()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:关闭所有定时任务
	 */
	public void shutdownJobs() {
		try {
			if (!scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void cronVerify(String cron) {
		try {
			if (!CronExpression.isValidExpression(cron)) {
				throw new RuntimeException("cron 表达式[" + (cron == null ? " " : cron) + "]不正确！");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("cron 表达式[" + (cron == null ? " " : cron) + "]不正确！");
		}
	}

	/**
	 * json格式：[{name:'名称1',value:'值1',notes:'描述1'},{name:'名称2',value:'值2',notes:
	 * '描述2'}]
	 *
	 * @param json
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public JobDataMap getJobDataMap(String json) {
		List<Map> list = JSON.parseArray(json, Map.class);
		JobDataMap jobDataMap = new JobDataMap();
		if (list == null || list.size() == 0) {
			return jobDataMap;
		}
		StringBuilder error = new StringBuilder();
		for (Map map : list) {
			// 校验参数name值不可重复
			String name = (String) map.get("name");
			if (jobDataMap.containsKey(name)) {
				error.append("参数名=[" + name + "]不可出现多次！\n");
				continue;
			}
			jobDataMap.put(name, map.get("value"));
		}
		if (error.toString().length() > 0) {
			throw new RuntimeException(error.toString());
		}
		return jobDataMap;
	}
}