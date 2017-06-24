package com.jyx.service;

import org.springframework.stereotype.Service;

import com.jyx.pojo.SysDict;

import java.util.List;

@Service
public interface SysDictService extends BaseService<SysDict>{

    List<SysDict> findById(List<String> ids) throws Exception;
}
