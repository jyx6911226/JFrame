package com.jyx.quartz;


import org.quartz.Job;
import org.springframework.stereotype.Component;

import com.jyx.pojo.SysJob;

import javax.annotation.Resource;

@Component
public class SchedulerManager {
	@Resource
	private SchedulerUtil schedulerUtil;

	/**
	 * 添加
	 *
	 * @param job
	 */
	public void addScheduler(SysJob job) {
		addOrModify(job);
	}
	public  void start(){
		schedulerUtil.startJobs();
	}
	/**
	 * 添加或修改
	 *
	 * @param job
	 */
	private void addOrModify(SysJob job) {
		// job类处理
		Class<? extends Job> jobclass = verify(job);
		// 添加到调度器
		if (null != jobclass) {
			if (schedulerUtil.isExist(getJobName(job))) {// 存在
				schedulerUtil.modifyJobTime(getJobName(job), job.getCron(),
						schedulerUtil.getJobDataMap(job.getJobParams()));
			} else {// 不存在
				schedulerUtil.addJob(getJobName(job), jobclass, job.getCron(),
						schedulerUtil.getJobDataMap(job.getJobParams()));
			}
			pauseJob(job);
		}
	}

	/**
	 * 如果isStart=false暂停一个任务，否则开启一个任务
	 *
	 * @param job
	 */
	public void pauseJob(SysJob job) {
		String jobName = getJobName(job);
		if (job.getStartFlag().equalsIgnoreCase("1")) {// 开启一个任务
			schedulerUtil.resumeJob(jobName);
		} else if (job.getStartFlag().equalsIgnoreCase("0")) {// 暂停一个任务
			schedulerUtil.pauseJob(jobName);
		}
	}

	private static String getJobName(SysJob job) {
		return job.getId();
	}

	/**
	 * 校验
	 *
	 * @param job
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<? extends Job> verify(SysJob job) {
		try {
			// cron 表达式验证
			schedulerUtil.cronVerify(job.getCron());
			// jobclass验证
			Class<?> clazz = Class.forName(job.getJobClass());
			Object instance = clazz.newInstance();
			if (instance instanceof ParamsVerify && job.getId() == null) {
				ParamsVerify paramsVerify = (ParamsVerify) instance;

				paramsVerify.verify(schedulerUtil.getJobDataMap(job.getJobParams()));// 校验
			}
			if (instance instanceof Job) {
				return (Class<? extends Job>) clazz;
			}
			return null;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			//logger.error(e.getMessage(), e);// 记录日志
			return null;
		}
	}

	/**
	 * 删除
	 *
	 * @param job
	 */
	public void deleteScheduler(SysJob job) {
		schedulerUtil.removeJob(getJobName(job));
	}

	/**
	 * 修改调度器cron
	 *
	 * @param job
	 */
	public void updateScheduler(SysJob job) {
		addOrModify(job);
	}
}
