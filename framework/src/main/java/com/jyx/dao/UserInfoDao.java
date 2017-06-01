package com.jyx.dao;

import com.jyx.pojo.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo, Long>{    
	/**
     * 通过username查找用户信息;
     */
    public UserInfo findByUsername(String username);
}	
