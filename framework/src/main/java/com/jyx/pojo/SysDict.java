package com.jyx.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 系统字典
 * */
@Entity
public class SysDict implements Serializable {
	private static final long serialVersionUID = 6217079955415626957L;
	
    @Id
    @Column(length = 32)
    @GenericGenerator(name="sysDictGenerator", strategy="uuid") //这个是hibernate的注解  
    @GeneratedValue(generator="sysDictGenerator") //使用uuid的生成策略 
    private String id;// 主键
    
    @NotNull
    @Column(unique = true, nullable = false, length = 32)
    private String code;// 字典代码
    
    @NotNull
    @Column(unique = true, nullable = false, length = 32)
    private String name;// 名称,文字描述

	@JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy="sysDict")
    private List<SysDictDetail> sysDictDetailList;//字典选项
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdTime;//创建时间
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;//最后更新时间
        
	public SysDict() {
		super();
	}

	public SysDict(String id, String code, String name, List<SysDictDetail> sysDictDetailList, Date createdTime,
			Date lastUpdateTime) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.sysDictDetailList = sysDictDetailList;
		this.createdTime = createdTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SysDictDetail> getSysDictDetailList() {
		return sysDictDetailList;
	}

	public void setSysDictDetailList(List<SysDictDetail> sysDictDetailList) {
		this.sysDictDetailList = sysDictDetailList;
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

	@Override
	public String toString() {
		return "SysDict [id=" + id + ", code=" + code + ", name=" + name + ", sysDictDetailList=" + sysDictDetailList
				+ ", createdTime=" + createdTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}
