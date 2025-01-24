package com.jacquinc.admin.sys.entity;

import com.jiujie.framework.mybatis.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "t_jj_sys_menu")
public class MenuEntity extends BaseEntity implements Serializable {
    /** 描述 */
    @Column(length = 255)
    private String description;

    /** 名称 */
    @Column(length = 32)
    private String name;

    /** 优先级 */
    @Column(length = 10)
    private Integer priority;

    /** 链接 */
    @Column(length = 255)
    private String url;

    /** 父Id */
    @Column(length = 32)
    private String parentId;

    /** 权限前缀码 */
    @Column(length = 32)
    private String permissionPrefixCode;

    /** 创建人 */
    @Column(length = 32)
    private String createUserId;

    /** 创建时间 */
    @Column(length = 35)
    private Date createTs;

    /** 更新人 */
    @Column(length = 32)
    private String updateUserId;

    /** 更新时间 */
    @Column(length = 35)
    private Date updateTs;

    /** 菜单权限： 1：超管 2：服务商 3：企业用户 4：有服务商的企业用户 5：政策服务商，多个用逗号隔开 */
    @Column(length = 30)
    private String isAdmin;

    /** 是否展示在服务商工作台 */
    @Column(length = 1)
    private String isPartner;

    private static final long serialVersionUID = 1L;

    /**
     * 描述
     * @return description
     */
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
        addSettedField("description");
    }

    /**
     * 名称
     * @return name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        addSettedField("name");
    }

    /**
     * 优先级
     * @return priority
     */
    @Column(name = "priority")
    public Integer getPriority() {
        return priority;
    }

    /**
     * 优先级
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
        addSettedField("priority");
    }

    /**
     * 链接
     * @return url
     */
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    /**
     * 链接
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
        addSettedField("url");
    }

    /**
     * 父Id
     * @return parentId
     */
    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    /**
     * 父Id
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
        addSettedField("parentId");
    }

    /**
     * 权限前缀码
     * @return permissionPrefixCode
     */
    @Column(name = "permission_prefix_code")
    public String getPermissionPrefixCode() {
        return permissionPrefixCode;
    }

    /**
     * 权限前缀码
     * @param permissionPrefixCode
     */
    public void setPermissionPrefixCode(String permissionPrefixCode) {
        this.permissionPrefixCode = permissionPrefixCode;
        addSettedField("permissionPrefixCode");
    }

    /**
     * 创建人
     * @return createUserId
     */
    @Column(name = "create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人
     * @param createUserId
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
        addSettedField("createUserId");
    }

    /**
     * 创建时间
     * @return createTs
     */
    @Column(name = "create_ts")
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * 创建时间
     * @param createTs
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
        addSettedField("createTs");
    }

    /**
     * 更新人
     * @return updateUserId
     */
    @Column(name = "update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人
     * @param updateUserId
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        addSettedField("updateUserId");
    }

    /**
     * 更新时间
     * @return updateTs
     */
    @Column(name = "update_ts")
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * 更新时间
     * @param updateTs
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
        addSettedField("updateTs");
    }

    /**
     * 菜单权限： 1：超管 2：服务商 3：企业用户 4：有服务商的企业用户 5：政策服务商，多个用逗号隔开
     * @return isAdmin
     */
    @Column(name = "is_admin")
    public String getIsAdmin() {
        return isAdmin;
    }

    /**
     * 菜单权限： 1：超管 2：服务商 3：企业用户 4：有服务商的企业用户 5：政策服务商，多个用逗号隔开
     * @param isAdmin
     */
    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
        addSettedField("isAdmin");
    }

    /**
     * 是否展示在服务商工作台
     * @return isPartner
     */
    @Column(name = "is_partner")
    public String getIsPartner() {
        return isPartner;
    }

    /**
     * 是否展示在服务商工作台
     * @param isPartner
     */
    public void setIsPartner(String isPartner) {
        this.isPartner = isPartner;
        addSettedField("isPartner");
    }

    /**
     * 获得当前实体 Mapper Class
     * @return Class
     */
    @Override
    public Class<?> obtainEntityMapperClass() {
        return MenuEntityMapper.class;
    }
}