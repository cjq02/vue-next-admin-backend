package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class CorpVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "纳税人识别号Taxpayer Identification Number")
    private String tin;

    @ApiModelProperty(value = "公司地址")
    private String address;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "公司邮箱")
    private String corpEmail;

    @ApiModelProperty(value = "创建时间")
    private Date createTs;

    @ApiModelProperty(value = "创建者")
    private String createUserId;

    @ApiModelProperty(value = "更新时间")
    private Date updateTs;

    @ApiModelProperty(value = "更新者")
    private String updateUserId;

    @ApiModelProperty(value = "企业是否已激活：0：否 1：是")
    private String isActive;

    @ApiModelProperty(value = "删除标志 0：未删除 1：已删除")
    private String delFlag;

    @ApiModelProperty(value = "企业类型   1：集团企业  2：板块公司、3：三级单位")
    private String corpType;

    @ApiModelProperty(value = "营业地址-省份编码")
    private Integer manageProvince;

    @ApiModelProperty(value = "营业地址-城市编码")
    private Integer manageCity;

    @ApiModelProperty(value = "营业地址-地区编码")
    private Integer manageArea;

    @ApiModelProperty(value = "父企业id")
    private String parentCorpId;

    @ApiModelProperty(value = "单位编码")
    private String code;

    @ApiModelProperty(value = "公司简称")
    private String shortName;

    @ApiModelProperty(value = "联系人")
    private String contactPerson;



    /**
     * 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 公司名称
     */
    public String getName() {
        return name;
    }

    /**
     * 公司名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 纳税人识别号Taxpayer Identification Number
     */
    public String getTin() {
        return tin;
    }

    /**
     * 纳税人识别号Taxpayer Identification Number
     */
    public void setTin(String tin) {
        this.tin = tin;
    }

    /**
     * 公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 公司地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 公司邮箱
     */
    public String getCorpEmail() {
        return corpEmail;
    }

    /**
     * 公司邮箱
     */
    public void setCorpEmail(String corpEmail) {
        this.corpEmail = corpEmail;
    }

    /**
     * 创建时间
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * 创建时间
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * 创建者
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建者
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * 更新时间
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    /**
     * 更新者
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新者
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 企业是否已激活：0：否 1：是
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * 企业是否已激活：0：否 1：是
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    /**
     * 删除标志 0：未删除 1：已删除
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标志 0：未删除 1：已删除
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 企业类型   1：集团企业  2：板块公司、3：三级单位
     */
    public String getCorpType() {
        return corpType;
    }

    /**
     * 企业类型   1：集团企业  2：板块公司、3：三级单位
     */
    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    /**
     * 营业地址-省份编码
     */
    public Integer getManageProvince() {
        return manageProvince;
    }

    /**
     * 营业地址-省份编码
     */
    public void setManageProvince(Integer manageProvince) {
        this.manageProvince = manageProvince;
    }

    /**
     * 营业地址-城市编码
     */
    public Integer getManageCity() {
        return manageCity;
    }

    /**
     * 营业地址-城市编码
     */
    public void setManageCity(Integer manageCity) {
        this.manageCity = manageCity;
    }

    /**
     * 营业地址-地区编码
     */
    public Integer getManageArea() {
        return manageArea;
    }

    /**
     * 营业地址-地区编码
     */
    public void setManageArea(Integer manageArea) {
        this.manageArea = manageArea;
    }

    /**
     * 父企业id
     */
    public String getParentCorpId() {
        return parentCorpId;
    }

    /**
     * 父企业id
     */
    public void setParentCorpId(String parentCorpId) {
        this.parentCorpId = parentCorpId;
    }

    /**
     * 单位编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 单位编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 公司简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 公司简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 联系人
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * 联系人
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

}