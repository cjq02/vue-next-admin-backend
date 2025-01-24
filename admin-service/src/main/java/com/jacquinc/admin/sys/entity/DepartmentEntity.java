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
@Table(name = "t_jj_sys_department")
public class DepartmentEntity extends BaseEntity implements Serializable {
    /** 人员姓名 */
    @Column(length = 50)
    private String name;

    /** 企业id */
    @Column(length = 32)
    private String corpId;

    /** 父级id */
    @Column(length = 32)
    private String parentId;

    /** 创建时间 */
    @Column(length = 22)
    private Date createTs;

    /** 创建者 */
    @Column(length = 32)
    private String createUserId;

    /** 更新时间 */
    @Column(length = 22)
    private Date updateTs;

    /** 更新者 */
    @Column(length = 32)
    private String updateUserId;

    /** 部门编号 */
    @Column(length = 20)
    private String code;

    /** 部门别称 */
    @Column(length = 50)
    private String nickName;

    /** 部门类型 1：管理部门、2：监督检查部门、3：生产技术部门、4：洗选检验部门 */
    @Column(length = 1)
    private String type;

    private static final long serialVersionUID = 1L;

    /**
     * 人员姓名
     * @return name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * 人员姓名
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        addSettedField("name");
    }

    /**
     * 企业id
     * @return corpId
     */
    @Column(name = "corp_id")
    public String getCorpId() {
        return corpId;
    }

    /**
     * 企业id
     * @param corpId
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
        addSettedField("corpId");
    }

    /**
     * 父级id
     * @return parentId
     */
    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    /**
     * 父级id
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
        addSettedField("parentId");
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
     * 创建者
     * @return createUserId
     */
    @Column(name = "create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建者
     * @param createUserId
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
        addSettedField("createUserId");
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
     * 更新者
     * @return updateUserId
     */
    @Column(name = "update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新者
     * @param updateUserId
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        addSettedField("updateUserId");
    }

    /**
     * 部门编号
     * @return code
     */
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    /**
     * 部门编号
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
        addSettedField("code");
    }

    /**
     * 部门别称
     * @return nickName
     */
    @Column(name = "nick_name")
    public String getNickName() {
        return nickName;
    }

    /**
     * 部门别称
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
        addSettedField("nickName");
    }

    /**
     * 部门类型 1：管理部门、2：监督检查部门、3：生产技术部门、4：洗选检验部门
     * @return type
     */
    @Column(name = "type")
    public String getType() {
        return type;
    }

    /**
     * 部门类型 1：管理部门、2：监督检查部门、3：生产技术部门、4：洗选检验部门
     * @param type
     */
    public void setType(String type) {
        this.type = type;
        addSettedField("type");
    }

    /**
     * 获得当前实体 Mapper Class
     * @return Class
     */
    @Override
    public Class<?> obtainEntityMapperClass() {
        return DepartmentEntityMapper.class;
    }
}