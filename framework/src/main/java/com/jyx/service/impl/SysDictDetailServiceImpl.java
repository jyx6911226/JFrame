package com.jyx.service.impl;

import com.jyx.dao.SysDictDetailDao;
import com.jyx.pojo.SysDictDetail;
import com.jyx.service.BaseService;
import com.jyx.service.SysDictDetailService;
import com.jyx.util.jpa.DynamicSpecifications;
import com.jyx.util.jpa.SearchFilter;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SysDictDetailServiceImpl implements BaseService<SysDictDetail>,SysDictDetailService{
	
	@Resource
	private SysDictDetailDao sysDictDetailDao;
	
	@Override
	public void save(SysDictDetail entity) throws Exception {
		if(entity.getCreatedTime() == null){
			entity.setCreatedTime(new Date());
		}
		entity.setLastUpdateTime(new Date());
		sysDictDetailDao.save(entity);
	}

	@Override
	public void delete(List<SysDictDetail> entities) throws Exception {
		sysDictDetailDao.delete(entities);
	}

	@Override
	public Page<SysDictDetail> findEntityPage(SysDictDetail searchEntity, Pageable page, List<SearchFilter> filters)
			throws Exception {
		Specification<SysDictDetail> specification = DynamicSpecifications.bySearchFilter(filters, SysDictDetail.class);		
	    return sysDictDetailDao.findAll(specification, page);
	}

}