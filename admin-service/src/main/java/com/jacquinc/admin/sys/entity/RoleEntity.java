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
@Table(name = "t_jj_sys_role")
public class RoleEntity extends BaseEntity implements Serializable {
    /** 名称 */
    @Column(length = 32)
    private String name;

    /** 创建日期 */
    @Column(length = 35)
    private Date createTs;

    /** 创建人id */
    @Column(length = 32)
    private String createUserId;

    /** 更新日期 */
    @Column(length = 35)
    private Date updateTs;

    /** 更新人id */
    @Column(length = 32)
    private String updateUserId;

    /** 角色类型 01:超级管理员 02：普通管理员 09：普通用户 */
    @Column(length = 5)
    private String roleType;

    /**  */
    @Column(length = 32)
    private String corpId;

    /** 工作流角色代号 */
    @Column(length = 64)
    private String workflowCode;

    private static final long serialVersionUID = 1L;

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
     * 创建日期
     * @return createTs
     */
    @Column(name = "create_ts")
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * 创建日期
     * @param createTs
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
        addSettedField("createTs");
    }

    /**
     * 创建人id
     * @return createUserId
     */
    @Column(name = "create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人id
     * @param createUserId
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
        addSettedField("createUserId");
    }

    /**
     * 更新日期
     * @return updateTs
     */
    @Column(name = "update_ts")
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * 更新日期
     * @param updateTs
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
        addSettedField("updateTs");
    }

    /**
     * 更新人id
     * @return updateUserId
     */
    @Column(name = "update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人id
     * @param updateUserId
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        addSettedField("updateUserId");
    }

    /**
     * 角色类型 01:超级管理员 02：普通管理员 09：普通用户
     * @return roleType
     */
    @Column(name = "role_type")
    public String getRoleType() {
        return roleType;
    }

    /**
     * 角色类型 01:超级管理员 02：普通管理员 09：普通用户
     * @param roleType
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
        addSettedField("roleType");
    }

    /**
     * 
     * @return corpId
     */
    @Column(name = "corp_id")
    public String getCorpId() {
        return corpId;
    }

    /**
     * 
     * @param corpId
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
        addSettedField("corpId");
    }

    /**
     * 工作流角色代号
     * @return workflowCode
     */
    @Column(name = "workflow_code")
    public String getWorkflowCode() {
        return workflowCode;
    }

    /**
     * 工作流角色代号
     * @param workflowCode
     */
    public void setWorkflowCode(String workflowCode) {
        this.workflowCode = workflowCode;
        addSettedField("workflowCode");
    }

    /**
     * 获得当前实体 Mapper Class
     * @return Class
     */
    @Override
    public Class<?> obtainEntityMapperClass() {
        return RoleEntityMapper.class;
    }
}