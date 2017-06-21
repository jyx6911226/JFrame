package com.jyx.dao;

import com.jyx.pojo.SysScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by jyx on 2017/6/20.
 */
public interface SysSchedulerDao extends JpaRepository<SysScheduler, String>, JpaSpecificationExecutor<SysScheduler> {

}
