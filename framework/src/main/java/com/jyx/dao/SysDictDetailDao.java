package com.jyx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jyx.pojo.SysDictDetail;

public interface SysDictDetailDao extends JpaRepository<SysDictDetail, String>, JpaSpecificationExecutor<SysDictDetail>{    

}	
