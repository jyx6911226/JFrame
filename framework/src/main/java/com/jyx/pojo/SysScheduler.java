package com.jyx.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 系统定时任务
 * */
@Entity
public class SysScheduler implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(length = 32)
    @GenericGenerator(name="sysSchedulerGenerator", strategy="uuid") //这个是hibernate的注解
    @GeneratedValue(generator="sysSchedulerGenerator") //使用uuid的生成策略
    private String id;// 主键
    /**
	 * 任务名称
     */
	@Column(unique = true, nullable = false, length = 32)
	private String name;

	/**
	 * 任务类
     */
	@Column(nullable = false)
	private String jobClass;
	/**
	 * 表达式
     */
	@Column(nullable = false, length = 64)
	private String cron;
	/**
	 * 是否启动
     */
	@Column(nullable = false)
	private Boolean startFlag;

	/**
	 * 任务参数
     */
	@Column
	private String jobParams;
	/**
	 * 任务说明
     */
	@Column(length = 255)
	private String notes;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdTime;//创建时间
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;//最后更新时间
    
    @ManyToOne
    @JoinColumn(name="creatorId",nullable = false)
    private UserInfo creator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public Boolean getStartFlag() {
		return startFlag;
	}

	public void setStartFlag(Boolean startFlag) {
		this.startFlag = startFlag;
	}

	public String getJobParams() {
		return jobParams;
	}

	public void setJobParams(String jobParams) {
		this.jobParams = jobParams;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public UserInfo getCreator() {
		return creator;
	}

	public void setCreator(UserInfo creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "SysScheduler [id=" + id + ", name=" + name + ", jobClass=" + jobClass + ", cron=" + cron + ", startFlag="
				+ startFlag + ", jobParams=" + jobParams + ", notes=" + notes + ", createdTime=" + createdTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", creator=" + creator + "]";
	}
}
