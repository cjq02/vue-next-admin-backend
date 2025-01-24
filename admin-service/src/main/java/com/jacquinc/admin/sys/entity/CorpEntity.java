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
@Table(name = "t_jj_sys_corp")
public class CorpEntity extends BaseEntity implements Serializable {
    /** 公司名称 */
    @Column(length = 200)
    private String name;

    /** 纳税人识别号Taxpayer Identification Number */
    @Column(length = 50)
    private String tin;

    /** 公司地址 */
    @Column(length = 500)
    private String address;

    /** 联系电话 */
    @Column(length = 32)
    private String phone;

    /** 公司邮箱 */
    @Column(length = 50)
    private String corpEmail;

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

    /** 企业是否已激活：0：否 1：是 */
    @Column(length = 1)
    private String isActive;

    /** 删除标志 0：未删除 1：已删除 */
    @Column(length = 1)
    private String delFlag;

    /** 企业类型   1：集团企业  2：板块公司、3：三级单位 */
    @Column(length = 1)
    private String corpType;

    /** 营业地址-省份编码 */
    @Column(length = 10)
    private Integer manageProvince;

    /** 营业地址-城市编码 */
    @Column(length = 10)
    private Integer manageCity;

    /** 营业地址-地区编码 */
    @Column(length = 10)
    private Integer manageArea;

    /** 父企业id */
    @Column(length = 32)
    private String parentCorpId;

    /** 单位编码 */
    @Column(length = 50)
    private String code;

    /** 公司简称 */
    @Column(length = 64)
    private String shortName;

    /** 联系人 */
    @Column(length = 100)
    private String contactPerson;

    private static final long serialVersionUID = 1L;

    /**
     * 公司名称
     * @return name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * 公司名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        addSettedField("name");
    }

    /**
     * 纳税人识别号Taxpayer Identification Number
     * @return tin
     */
    @Column(name = "tin")
    public String getTin() {
        return tin;
    }

    /**
     * 纳税人识别号Taxpayer Identification Number
     * @param tin
     */
    public void setTin(String tin) {
        this.tin = tin;
        addSettedField("tin");
    }

    /**
     * 公司地址
     * @return address
     */
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    /**
     * 公司地址
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
        addSettedField("address");
    }

    /**
     * 联系电话
     * @return phone
     */
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    /**
     * 联系电话
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
        addSettedField("phone");
    }

    /**
     * 公司邮箱
     * @return corpEmail
     */
    @Column(name = "corp_email")
    public String getCorpEmail() {
        return corpEmail;
    }

    /**
     * 公司邮箱
     * @param corpEmail
     */
    public void setCorpEmail(String corpEmail) {
        this.corpEmail = corpEmail;
        addSettedField("corpEmail");
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
     * 企业是否已激活：0：否 1：是
     * @return isActive
     */
    @Column(name = "is_active")
    public String getIsActive() {
        return isActive;
    }

    /**
     * 企业是否已激活：0：否 1：是
     * @param isActive
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
        addSettedField("isActive");
    }

    /**
     * 删除标志 0：未删除 1：已删除
     * @return delFlag
     */
    @Column(name = "del_flag")
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标志 0：未删除 1：已删除
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        addSettedField("delFlag");
    }

    /**
     * 企业类型   1：集团企业  2：板块公司、3：三级单位
     * @return corpType
     */
    @Column(name = "corp_type")
    public String getCorpType() {
        return corpType;
    }

    /**
     * 企业类型   1：集团企业  2：板块公司、3：三级单位
     * @param corpType
     */
    public void setCorpType(String corpType) {
        this.corpType = corpType;
        addSettedField("corpType");
    }

    /**
     * 营业地址-省份编码
     * @return manageProvince
     */
    @Column(name = "manage_province")
    public Integer getManageProvince() {
        return manageProvince;
    }

    /**
     * 营业地址-省份编码
     * @param manageProvince
     */
    public void setManageProvince(Integer manageProvince) {
        this.manageProvince = manageProvince;
        addSettedField("manageProvince");
    }

    /**
     * 营业地址-城市编码
     * @return manageCity
     */
    @Column(name = "manage_city")
    public Integer getManageCity() {
        return manageCity;
    }

    /**
     * 营业地址-城市编码
     * @param manageCity
     */
    public void setManageCity(Integer manageCity) {
        this.manageCity = manageCity;
        addSettedField("manageCity");
    }

    /**
     * 营业地址-地区编码
     * @return manageArea
     */
    @Column(name = "manage_area")
    public Integer getManageArea() {
        return manageArea;
    }

    /**
     * 营业地址-地区编码
     * @param manageArea
     */
    public void setManageArea(Integer manageArea) {
        this.manageArea = manageArea;
        addSettedField("manageArea");
    }

    /**
     * 父企业id
     * @return parentCorpId
     */
    @Column(name = "parent_corp_id")
    public String getParentCorpId() {
        return parentCorpId;
    }

    /**
     * 父企业id
     * @param parentCorpId
     */
    public void setParentCorpId(String parentCorpId) {
        this.parentCorpId = parentCorpId;
        addSettedField("parentCorpId");
    }

    /**
     * 单位编码
     * @return code
     */
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    /**
     * 单位编码
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
        addSettedField("code");
    }

    /**
     * 公司简称
     * @return shortName
     */
    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    /**
     * 公司简称
     * @param shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
        addSettedField("shortName");
    }

    /**
     * 联系人
     * @return contactPerson
     */
    @Column(name = "contact_person")
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * 联系人
     * @param contactPerson
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        addSettedField("contactPerson");
    }

    /**
     * 获得当前实体 Mapper Class
     * @return Class
     */
    @Override
    public Class<?> obtainEntityMapperClass() {
        return CorpEntityMapper.class;
    }
}