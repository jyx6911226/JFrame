package com.jyx.service.impl;

import com.jyx.dao.SysSchedulerDao;
import com.jyx.pojo.SysScheduler;
import com.jyx.pojo.UserInfo;
import com.jyx.quartz.SchedulerManager;
import com.jyx.service.BaseService;
import com.jyx.service.SysSchedulerService;
import com.jyx.util.jpa.DynamicSpecifications;
import com.jyx.util.jpa.SearchFilter;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysSchedulerServiceImpl implements BaseService<SysScheduler>,SysSchedulerService {
	
	@Resource
	private SysSchedulerDao sysSchedulerDao;
    @Resource
    SchedulerManager schedulerManager;

	@Override
	public void save(SysScheduler entity) throws Exception {

		if(entity.getCreator() == null || entity.getCreator().getId() == null) {
            UserInfo userinfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
            entity.setCreator(userinfo);
		}

		if(entity.getCreatedTime() == null) {
			entity.setCreatedTime(new Date());
		}

		entity.setLastUpdateTime(new Date());
		sysSchedulerDao.save(entity);
	}

	@Override
	public void delete(List<SysScheduler> entities) throws Exception {
		sysSchedulerDao.delete(entities);
	}

	@Override
	public Page<SysScheduler> findEntityPage(SysScheduler searchEntity, Pageable page, List<SearchFilter> filters)
			throws Exception {
		Specification<SysScheduler> specification = DynamicSpecifications.bySearchFilter(filters, SysScheduler.class);
	    return sysSchedulerDao.findAll(specification, page);
	}

	@Override
	public void deploySysScheduler(SysScheduler sysScheduler) throws Exception {
        schedulerManager.addScheduler(sysScheduler);
	}

	@Transactional
	@Override
	public void saveAndDeploySysScheduler(SysScheduler sysScheduler) throws Exception {
        //step1:保存任务
        if(sysScheduler.getId() == null || "".equals(sysScheduler.getId().trim())) {
            UUID uuid = UUID.randomUUID();
            sysScheduler.setId(uuid.toString());
        }
		this.save(sysScheduler);
		//step2：部署任务
		this.deploySysScheduler(sysScheduler);
	}

	@Override
	public void removeSysScheduler(List<SysScheduler> sysSchedulerList) throws Exception {
		if(sysSchedulerList != null) {
		    for(SysScheduler sysScheduler : sysSchedulerList){
                schedulerManager.deleteScheduler(sysScheduler);
            }
        }
	}

	@Transactional
	@Override
	public void deleteSysSchedulerAndRemove(List<SysScheduler> sysSchedulerList) throws Exception {

		//step1：移除任务
		this.removeSysScheduler(sysSchedulerList);
		//step2:删除任务
		this.delete(sysSchedulerList);
	}

    @Override
    public List<SysScheduler> findByStartFlag(Boolean startFlag) throws Exception {
        return sysSchedulerDao.findByStartFlag(startFlag);
    }
}