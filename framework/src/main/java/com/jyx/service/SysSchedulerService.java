package com.jyx.service;

import com.jyx.pojo.SysScheduler;
import org.springframework.stereotype.Service;

@Service
public interface SysSchedulerService extends BaseService<SysScheduler>{

    /**
     * 保存任务并部署任务
     * */
    void saveSysSchedulerAndDeploy(SysScheduler sysScheduler) throws Exception;

    /**
     * 部署任务
     * */
    void deploySysScheduler(SysScheduler sysScheduler) throws Exception;
}
