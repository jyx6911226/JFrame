package com.jyx.dao;

import com.jyx.pojo.SysPermission;
import com.jyx.pojo.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/2/8.
 */
public interface SysPermissionDao extends JpaRepository<SysPermission, Long>, JpaSpecificationExecutor<SysPermission> {
}
