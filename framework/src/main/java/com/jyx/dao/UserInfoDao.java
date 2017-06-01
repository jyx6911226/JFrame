package com.jyx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jyx.pojo.UserInfo;

public interface UserInfoDao extends JpaRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo> {
    /**
     * 通过username查找用户信息;
     */
    public UserInfo findByUsername(String username);
}	
