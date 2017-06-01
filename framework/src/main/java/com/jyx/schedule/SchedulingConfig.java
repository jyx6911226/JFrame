package com.jyx.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务控制类
 * 注意在定时任务中，jpa懒加载会报错，因为session已经关闭
 * 解决方法，在方法上添加@Transactional注解或不使用懒加载(不推荐，因为会影响效率)
 * 如果不回滚的话加上(noRollbackFor=Exception.class)就好。
 * 第三种解决办法，需要配置类，请见博客：
 * http://blog.csdn.net/u013815546/article/details/53032445
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
//	@Resource
//	private OneObjService oneObjService;
//	@Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
//	//@Scheduled(cron = "0 0 12 * * ?")
//	@Transactional(noRollbackFor=Exception.class)
//	public void scheduler() {
//		System.out.println(">>>>>>>>> SchedulingConfig.scheduler() start");
//		OneObj1 one = oneObjService.getById("402882e65828227b0158282296430000");
//		System.out.println("one.getManyObj1s().size()====>"+one.getManyObj1s().size());
//		System.out.println(">>>>>>>>> SchedulingConfig.scheduler() end");
//	}
}