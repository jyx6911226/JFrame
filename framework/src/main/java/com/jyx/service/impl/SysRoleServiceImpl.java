package com.jyx.service.impl;

import com.jyx.dao.SysRoleDao;
import com.jyx.pojo.SysRole;
import com.jyx.service.SysRoleService;
import com.jyx.util.jpa.DynamicSpecifications;
import com.jyx.util.jpa.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    @Override
    public void save(SysRole obj) throws Exception {
        sysRoleDao.save(obj);
    }

    @Override
    public void delete(List<SysRole> objs) throws Exception {
        sysRoleDao.delete(objs);
    }

    @Override
    public Page<SysRole> findList(SysRole searchObj, Pageable page, List<SearchFilter> filters) throws Exception {
        Specification<SysRole> specification = DynamicSpecifications.bySearchFilter(filters, SysRole.class);
        return sysRoleDao.findAll(specification, page);
    }

}