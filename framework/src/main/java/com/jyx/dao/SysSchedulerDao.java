package com.jyx.dao;

import com.jyx.pojo.SysScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by jyx on 2017/6/20.
 */
public interface SysSchedulerDao extends JpaRepository<SysScheduler, String>, JpaSpecificationExecutor<SysScheduler> {
    /**
     * 通过状态查找任务信息;
     */
    public List<SysScheduler> findByStartFlag(Boolean startFlag);
}
