package com.jyx.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 系统字典明细（选项）
 * */
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"sysDictId", "parentId", "label"}),
		@UniqueConstraint(columnNames = {"sysDictId", "parentId", "value"})
})
public class SysDictDetail implements Serializable {
	private static final long serialVersionUID = 6217079955415626957L;
	
    @Id
    @Column(length = 32)
    @GenericGenerator(name="sysDictDetailGenerator", strategy="uuid") //这个是hibernate的注解  
    @GeneratedValue(generator="sysDictDetailGenerator") //使用uuid的生成策略 
    private String id;// 主键
    
    @NotNull
    @Column(nullable = false, length = 32)
    private String label;// 标注
    
    @NotNull
    @Column(nullable = false, length = 32)
    private String value;// 选项值
    
    @NotNull
    @Column(nullable = false, length = 32)
    private String parentId;     // 父编号
    @NotNull
    @Column(nullable = false, length = 255)
    private String parentIds;    // 父编号列表

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name="sysDictId")
    private SysDict sysDict;//所属的字典
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdTime;//创建时间
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;//最后更新时间

	public SysDictDetail() {
		super();
	}
	
	public SysDictDetail(String id, String label, String value, String parentId, String parentIds, SysDict sysDict,
			Date createdTime, Date lastUpdateTime) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
		this.parentId = parentId;
		this.parentIds = parentIds;
		this.sysDict = sysDict;
		this.createdTime = createdTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SysDict getSysDict() {
		return sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Override
	public String toString() {
		return "SysDictDetail [id=" + id + ", label=" + label + ", value=" + value + ", parentId=" + parentId
				+ ", parentIds=" + parentIds + ", sysDict=" + sysDict + ", createdTime=" + createdTime
				+ ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}
