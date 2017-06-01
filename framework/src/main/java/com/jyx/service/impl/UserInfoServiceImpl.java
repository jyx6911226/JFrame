package com.jyx.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.jyx.dao.UserInfoDao;
import com.jyx.pojo.UserInfo;
import com.jyx.service.UserInfoService;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo,Long> implements UserInfoService {
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}