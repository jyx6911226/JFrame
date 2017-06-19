package com.jyx.quartz;

import org.quartz.JobDataMap;

/**
 * 参数校验
 */
public interface ParamsVerify {
	/**
	 * 参数校验
	 * @param jobDataMap
	 */
	void verify(JobDataMap jobDataMap);
}
