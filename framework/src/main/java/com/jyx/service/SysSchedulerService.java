package com.jyx.service;

import com.jyx.pojo.SysScheduler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysSchedulerService extends BaseService<SysScheduler>{

    /**
     * 保存任务并部署任务
     * */
    void saveAndDeploySysScheduler(SysScheduler sysScheduler) throws Exception;

    /**
     * 部署任务
     * */
    void deploySysScheduler(SysScheduler sysScheduler) throws Exception;

    /**
     * 删除任务
     * */
    void removeSysScheduler(List<SysScheduler> sysSchedulerList) throws Exception;

    /**
     * 删除并移除任务
     * */
    void deleteSysSchedulerAndRemove(List<SysScheduler> sysSchedulerList) throws Exception;

    /**
     * 通过状态查找任务信息;
     */
    List<SysScheduler> findByStartFlag(Boolean startFlag) throws Exception;
}
