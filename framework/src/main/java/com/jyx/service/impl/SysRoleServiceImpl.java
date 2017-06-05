package com.jyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jyx.dao.SysRoleDao;
import com.jyx.pojo.SysRole;
import com.jyx.service.BaseService;
import com.jyx.service.SysRoleService;
import com.jyx.util.jpa.DynamicSpecifications;
import com.jyx.util.jpa.SearchFilter;

@Service
public class SysRoleServiceImpl implements BaseService<SysRole>,SysRoleService {
	
	@Resource
	private SysRoleDao sysRoleDao;
	
	@Override
	public void save(SysRole entity) throws Exception {
		sysRoleDao.save(entity);
	}

	@Override
	public void delete(List<SysRole> entities) throws Exception {
		sysRoleDao.delete(entities);
	}

	@Override
	public Page<SysRole> findEntityPage(SysRole searchEntity, Pageable page, List<SearchFilter> filters)
			throws Exception {
		Specification<SysRole> specification = DynamicSpecifications.bySearchFilter(filters, SysRole.class);		
	    return sysRoleDao.findAll(specification, page);
	}
}