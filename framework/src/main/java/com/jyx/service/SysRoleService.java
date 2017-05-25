package com.jyx.service;

import com.jyx.pojo.SysRole;
import com.jyx.util.jpa.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysRoleService {
    void save(SysRole obj) throws Exception;

    void delete(List<SysRole> obj) throws Exception;

    Page<SysRole> findList(SysRole searchObj, Pageable page, List<SearchFilter> filters) throws Exception;
}
