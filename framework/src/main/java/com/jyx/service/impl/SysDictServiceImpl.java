package com.jyx.service.impl;

import com.jyx.dao.SysDictDao;
import com.jyx.pojo.SysDict;
import com.jyx.service.BaseService;
import com.jyx.service.SysDictDetailService;
import com.jyx.service.SysDictService;
import com.jyx.util.jpa.DynamicSpecifications;
import com.jyx.util.jpa.SearchFilter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl implements BaseService<SysDict>,SysDictService{
	
	@Resource
	private SysDictDao sysDictDao;

	@Resource
	private SysDictDetailService sysDictDetailService;

	@Override
	public void save(SysDict entity) throws Exception {
		if(entity.getCreatedTime() == null){
			entity.setCreatedTime(new Date());
		}
		entity.setLastUpdateTime(new Date());
		sysDictDao.save(entity);
	}

	@Override
	public void delete(List<SysDict> entities) throws Exception {
		sysDictDao.delete(entities);
	}

	@Override
	public Page<SysDict> findEntityPage(SysDict searchEntity, Pageable page, List<SearchFilter> filters)
			throws Exception {
		Specification<SysDict> specification = DynamicSpecifications.bySearchFilter(filters, SysDict.class);		
	    return sysDictDao.findAll(specification, page);
	}

    @Override
    public List<SysDict> findById(List<String> ids) throws Exception {
        return sysDictDao.findById(ids);
    }
}