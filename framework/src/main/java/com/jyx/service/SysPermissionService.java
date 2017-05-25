package com.jyx.service;

import com.jyx.pojo.SysPermission;
import com.jyx.util.jpa.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysPermissionService {
    Page<SysPermission> findList(SysPermission searchObj, Pageable page, List<SearchFilter> filters) throws Exception;

    void save(SysPermission obj) throws Exception;

    void delete(List<SysPermission> objs) throws Exception;
}
