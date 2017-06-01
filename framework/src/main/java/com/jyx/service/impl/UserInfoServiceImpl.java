package com.jyx.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyx.dao.UserInfoDao;
import com.jyx.pojo.UserInfo;
import com.jyx.service.UserInfoService;
import com.jyx.util.jpa.DynamicSpecifications;
import com.jyx.util.jpa.SearchFilter;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {

        return userInfoDao.findByUsername(username);
    }

    /**
     * 添加用户
     */
    @Override
    @Transactional
    public void save(UserInfo userInfo) throws Exception {
        //userInfo.setCreateTime(new Timestamp(new Date().getTime()));
        userInfoDao.save(userInfo);
    }

    @Override
    public void delete(List<UserInfo> userInfos) throws Exception {
        userInfoDao.delete(userInfos);
    }

    @Override
    public Page<UserInfo> findUserInfoList(UserInfo searchObj, Pageable page, List<SearchFilter> filters) throws Exception {
        Specification<UserInfo> specification = DynamicSpecifications.bySearchFilter(filters, UserInfo.class);
        return userInfoDao.findAll(specification, page);
    }
}