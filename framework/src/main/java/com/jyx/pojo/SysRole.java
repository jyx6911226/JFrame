package com.jyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 系统角色实体类;
 */
@Entity
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id; // 编号
    @NotNull
    @Column(unique = true, nullable = false)
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    @NotNull
    @Column(unique = true, nullable = false)
    private String name; // 角色名称
    @NotNull
    @Column(nullable = false)
    private String description; // 角色描述,UI界面显示使用
    private Boolean available;  // 是否可用,如果不可用将不会添加给用户
    // 角色 -- 权限关系：多对多关系;
    @JsonIgnore
    @ManyToMany(cascade={ CascadeType.ALL},fetch=FetchType.EAGER)
    @JoinTable(name="sys_role_permission" ,
            joinColumns=@JoinColumn(name="role_id") ,
            inverseJoinColumns=@JoinColumn(name="permission_id"))
    private List<SysPermission> permissions;
    // 用户 - 角色关系定义;
    @JsonIgnore
    @ManyToMany(cascade={ CascadeType.PERSIST})
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<UserInfo> userInfos;// 一个角色对应多个用户

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", permissions=" + permissions +
                ", userInfos=" + userInfos +
                '}';
    }
}
