package com.jyx.service.impl;

import com.jyx.dao.SysPermissionDao;
import com.jyx.pojo.SysPermission;
import com.jyx.service.BaseService;
import com.jyx.service.SysPermissionService;
import com.jyx.util.jpa.DynamicSpecifications;
import com.jyx.util.jpa.SearchFilter;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SysPermissionServiceImpl implements BaseService<SysPermission>,SysPermissionService{
	
	@Resource
	private SysPermissionDao sysPermissionDao;
	
	@Override
	public void save(SysPermission entity) throws Exception {
		sysPermissionDao.save(entity);
	}

	@Override
	public void delete(List<SysPermission> entities) throws Exception {
		sysPermissionDao.delete(entities);
	}

	@Override
	public Page<SysPermission> findEntityPage(SysPermission searchEntity, Pageable page, List<SearchFilter> filters)
			throws Exception {
		Specification<SysPermission> specification = DynamicSpecifications.bySearchFilter(filters, SysPermission.class);		
	    return sysPermissionDao.findAll(specification, page);
	}

}