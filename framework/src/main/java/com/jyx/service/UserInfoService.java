package com.jyx.service;

import org.springframework.stereotype.Service;
import com.jyx.pojo.UserInfo;

@Service
public interface UserInfoService extends BaseService<UserInfo>{
    UserInfo findByUsername(String username) throws Exception;
}
