package com.jyx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jyx.pojo.SysDict;

public interface SysDictDao extends JpaRepository<SysDict, String>, JpaSpecificationExecutor<SysDict>{    

}	
