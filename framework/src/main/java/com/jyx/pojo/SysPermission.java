package com.jyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 权限实体类;
 */
@Entity
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;// 主键
    @NotNull
    @Column(unique = true, nullable = false)
    private String name;// 名称.
    @NotNull
    @Column(nullable = false, columnDefinition = "enum('page','interface','button','node')")
    private String resourceType;// 资源类型，[page|menu|button|node]
    private String resourceUrl;// 资源路径.
    @NotNull
    @Column(nullable = false, unique = true)
    private String permission;   // 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    @NotNull
    @Column(nullable = false)
    private Long parentId;        // 父编号
    @Column(nullable = false)
    private String parentIds;    // 父编号列表
    private Boolean available = Boolean.FALSE;
    @JsonIgnore
    @ManyToMany(cascade={ CascadeType.PERSIST})
    @JoinTable(name="sys_role_permission" ,
            joinColumns=@JoinColumn(name="permission_id") ,
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private List<SysRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", available=" + available +
                ", roles=" + roles +
                '}';
    }
}
