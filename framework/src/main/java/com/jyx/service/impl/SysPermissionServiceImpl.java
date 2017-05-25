package com.jyx.service.impl;

import com.jyx.dao.SysPermissionDao;
import com.jyx.pojo.SysPermission;
import com.jyx.service.SysPermissionService;
import com.jyx.util.jpa.DynamicSpecifications;
import com.jyx.util.jpa.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    private SysPermissionDao sysPermissionDao;

    @Override
    public void save(SysPermission obj) throws Exception {
        sysPermissionDao.save(obj);
    }

    @Override
    public void delete(List<SysPermission> objs) throws Exception {
        sysPermissionDao.delete(objs);
    }

    @Override
    public Page<SysPermission> findList(SysPermission searchObj, Pageable page, List<SearchFilter> filters) throws Exception {
        Specification<SysPermission> specification = DynamicSpecifications.bySearchFilter(filters, SysPermission.class);
        return sysPermissionDao.findAll(specification, page);
    }

}