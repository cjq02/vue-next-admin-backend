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
@Table(name = "t_jj_sys_code")
public class CodeEntity extends BaseEntity implements Serializable {
    /** 字典分类代码，如产品类型代码PRODUCT_TYPE */
    @Column(length = 32)
    private String typeCode;

    /** 字典分类名称，如产品类型 */
    @Column(length = 60)
    private String typeName;

    /** 列表的中文值，如贷款 */
    @Column(length = 200)
    private String configName;

    /** 列表值的编码，如LB */
    @Column(length = 32)
    private String configCode;

    /** 列表中显示的顺序号，如1,2,3 */
    @Column(length = 5)
    private Short indexNo;

    /** 描述或备注信息 */
    @Column(length = 200)
    private String remark;

    /** 创建人id */
    @Column(length = 32)
    private String createUserId;

    /** 创建日期 */
    @Column(length = 29)
    private Date createTs;

    /** 更新人id */
    @Column(length = 32)
    private String updateUserId;

    /** 更新日期 */
    @Column(length = 29)
    private Date updateTs;

    /** 是否默认（如默认选中、默认展示等）：0-否 1-是 */
    @Column(length = 1)
    private String isDefault;

    /** 父编码 */
    @Column(length = 10)
    private String parentCode;

    private static final long serialVersionUID = 1L;

    /**
     * 字典分类代码，如产品类型代码PRODUCT_TYPE
     * @return typeCode
     */
    @Column(name = "type_code")
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 字典分类代码，如产品类型代码PRODUCT_TYPE
     * @param typeCode
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        addSettedField("typeCode");
    }

    /**
     * 字典分类名称，如产品类型
     * @return typeName
     */
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    /**
     * 字典分类名称，如产品类型
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
        addSettedField("typeName");
    }

    /**
     * 列表的中文值，如贷款
     * @return configName
     */
    @Column(name = "config_name")
    public String getConfigName() {
        return configName;
    }

    /**
     * 列表的中文值，如贷款
     * @param configName
     */
    public void setConfigName(String configName) {
        this.configName = configName;
        addSettedField("configName");
    }

    /**
     * 列表值的编码，如LB
     * @return configCode
     */
    @Column(name = "config_code")
    public String getConfigCode() {
        return configCode;
    }

    /**
     * 列表值的编码，如LB
     * @param configCode
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
        addSettedField("configCode");
    }

    /**
     * 列表中显示的顺序号，如1,2,3
     * @return indexNo
     */
    @Column(name = "index_no")
    public Short getIndexNo() {
        return indexNo;
    }

    /**
     * 列表中显示的顺序号，如1,2,3
     * @param indexNo
     */
    public void setIndexNo(Short indexNo) {
        this.indexNo = indexNo;
        addSettedField("indexNo");
    }

    /**
     * 描述或备注信息
     * @return remark
     */
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    /**
     * 描述或备注信息
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
        addSettedField("remark");
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
     * 是否默认（如默认选中、默认展示等）：0-否 1-是
     * @return isDefault
     */
    @Column(name = "is_default")
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 是否默认（如默认选中、默认展示等）：0-否 1-是
     * @param isDefault
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
        addSettedField("isDefault");
    }

    /**
     * 父编码
     * @return parentCode
     */
    @Column(name = "parent_code")
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 父编码
     * @param parentCode
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
        addSettedField("parentCode");
    }

    /**
     * 获得当前实体 Mapper Class
     * @return Class
     */
    @Override
    public Class<?> obtainEntityMapperClass() {
        return CodeEntityMapper.class;
    }
}