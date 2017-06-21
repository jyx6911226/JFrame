package com.jyx.quartz;


import com.jyx.pojo.SysScheduler;
import org.quartz.Job;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SchedulerManager {
	@Resource
	private SchedulerUtil schedulerUtil;

	/**
	 * 添加
	 *
	 * @param sysScheduler
	 */
	public void addScheduler(SysScheduler sysScheduler) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
		addOrModify(sysScheduler);
	}
	public  void start(){
		schedulerUtil.startJobs();
	}
	/**
	 * 添加或修改
	 *
	 * @param sysScheduler
	 */
	private void addOrModify(SysScheduler sysScheduler) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// job类处理
		Class<? extends Job> jobclass = verify(sysScheduler);
		// 添加到调度器
		if (null != jobclass) {
			if (schedulerUtil.isExist(getJobName(sysScheduler))) {// 存在
				schedulerUtil.modifyJobTime(getJobName(sysScheduler), sysScheduler.getCron(),
						schedulerUtil.getJobDataMap(sysScheduler.getJobParams()));
			} else {// 不存在
				schedulerUtil.addJob(getJobName(sysScheduler), jobclass, sysScheduler.getCron(),
						schedulerUtil.getJobDataMap(sysScheduler.getJobParams()));
			}
			pauseJob(sysScheduler);
		}
	}

	/**
	 * 如果isStart=false暂停一个任务，否则开启一个任务
	 *
	 * @param sysScheduler
	 */
	public void pauseJob(SysScheduler sysScheduler) {
		String jobName = getJobName(sysScheduler);
		if (sysScheduler.getStartFlag()) {// 开启一个任务
			schedulerUtil.resumeJob(jobName);
		} else if (!sysScheduler.getStartFlag()) {// 暂停一个任务
			schedulerUtil.pauseJob(jobName);
		}
	}

	private static String getJobName(SysScheduler sysScheduler) {
		return sysScheduler.getId();
	}

	/**
	 * 校验
	 *
	 * @param sysScheduler
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<? extends Job> verify(SysScheduler sysScheduler) throws ClassNotFoundException,InstantiationException,IllegalAccessException{
		try {
			// cron 表达式验证
			schedulerUtil.cronVerify(sysScheduler.getCron());
			// jobclass验证
			Class<?> clazz = Class.forName(sysScheduler.getJobClass());
			Object instance = clazz.newInstance();
			if (instance instanceof ParamsVerify && sysScheduler.getId() == null) {
				ParamsVerify paramsVerify = (ParamsVerify) instance;

				paramsVerify.verify(schedulerUtil.getJobDataMap(sysScheduler.getJobParams()));// 校验
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
	 * @param sysScheduler
	 */
	public void deleteScheduler(SysScheduler sysScheduler) {
		schedulerUtil.removeJob(getJobName(sysScheduler));
	}

	/**
	 * 修改调度器cron
	 *
	 * @param sysScheduler
	 */
	public void updateScheduler(SysScheduler sysScheduler) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
		addOrModify(sysScheduler);
	}
}
