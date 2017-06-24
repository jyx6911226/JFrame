package com.jyx.dao;

import com.jyx.pojo.SysDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jyx.pojo.SysDictDetail;

import java.util.List;

public interface SysDictDetailDao extends JpaRepository<SysDictDetail, String>, JpaSpecificationExecutor<SysDictDetail>{
}	
