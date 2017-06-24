package com.jyx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jyx.pojo.SysDict;

import java.util.List;

public interface SysDictDao extends JpaRepository<SysDict, String>, JpaSpecificationExecutor<SysDict>{    
    List<SysDict> findById(List<String> ids);
}	
