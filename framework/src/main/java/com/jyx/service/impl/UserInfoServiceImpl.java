package com.jyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jyx.dao.UserInfoDao;
import com.jyx.pojo.UserInfo;
import com.jyx.service.BaseService;
import com.jyx.service.UserInfoService;
import com.jyx.util.jpa.DynamicSpecifications;
import com.jyx.util.jpa.SearchFilter;

@Service
public class UserInfoServiceImpl implements BaseService<UserInfo>,UserInfoService {
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }

	@Override
	public void save(UserInfo entity) throws Exception {
		userInfoDao.save(entity);		
	}

	@Override
	public void delete(List<UserInfo> entities) throws Exception {
		userInfoDao.delete(entities);
	}

	@Override
	public Page<UserInfo> findEntityPage(UserInfo searchEntity, Pageable page, List<SearchFilter> filters)
			throws Exception {
		Specification<UserInfo> specification = DynamicSpecifications.bySearchFilter(filters, UserInfo.class);		
	    return userInfoDao.findAll(specification, page);
	}
}