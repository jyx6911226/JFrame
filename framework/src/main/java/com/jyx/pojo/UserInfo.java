package com.jyx.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户信息
 */
@Entity
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue
	private Long id; // 用户id;
	@Column(unique = true, nullable = false, length = 32)
	private String username; // 账号
	@Column(length = 32)
	private String name; // 名称（昵称或者真实姓名，不同系统不同定义）
	@NotNull
	@Column(nullable = false)
	@JsonIgnore // json转换忽略此字段
	private String password; // 密码;
	@Transient
	@JsonIgnore
	private final String salt = "jyx"; // 加密密码的盐
	@Email
	@Column(length = 32)
	private String email; // 邮箱
	@Column(length = 32)
	private String telephone; // 手机号
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "BLOB")
	private byte[] image; // 头像

	// @Column(nullable = false)
	// @Temporal(TemporalType.TIMESTAMP)
	// private Timestamp createTime; //账号创建时间
	// @Temporal(TemporalType.TIMESTAMP)
	// private Timestamp lastUpdateTime; //最后更新时间
	/**
	 * -1. 被删除 1. 创建（未认证） 2. 已认证 3. 被锁定
	 */
	private Byte state;
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL },fetch=FetchType.EAGER)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<SysRole> roleList; // 一个用户具有多个角色

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	// public Date getCreateTime() {
	// return createTime;
	// }
	//
	// public void setCreateTime(Timestamp createTime) {
	// this.createTime = createTime;
	// }
	//
	// public Date getLastUpdateTime() {
	// return lastUpdateTime;
	// }
	//
	// public void setLastUpdateTime(Timestamp lastUpdateTime) {
	// this.lastUpdateTime = lastUpdateTime;
	// }

	/**
	 * 密码盐.
	 *
	 * @return
	 */
	@JsonIgnore
	public String getCredentialsSalt() {
		return this.salt;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password + ", salt="
				+ salt + ", state=" + state + "]";
	}

}