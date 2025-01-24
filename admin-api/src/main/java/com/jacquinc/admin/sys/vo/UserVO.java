package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class UserVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "企业id")
    private String corpId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "密码加密 盐")
    private String salt;

    @ApiModelProperty(value = "是否激活  0-失效 1-激活")
    private String active;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像id")
    private String photoId;

    @ApiModelProperty(value = "工号")
    private String jobNo;

    @ApiModelProperty(value = "创建时间")
    private Date createTs;

    @ApiModelProperty(value = "创建者")
    private String createUserId;

    @ApiModelProperty(value = "更新时间")
    private Date updateTs;

    @ApiModelProperty(value = "更新者")
    private String updateUserId;

    @ApiModelProperty(value = "用户类型 1:超级管理员 2：项目负责人 3：精益办成员 4：专家")
    private String userType;

    @ApiModelProperty(value = "部门id")
    private String departmentId;

    @ApiModelProperty(value = "联系方式，手机、固定电话、邮箱等等")
    private String contact;

    @ApiModelProperty(value = "删除标志 0：未删除、1：已删除")
    private String delFlag;

    @ApiModelProperty(value = "删除时间")
    private Date delTs;

    @ApiModelProperty(value = "删除者")
    private String delUserId;

    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTs;

    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;

    @ApiModelProperty(value = "是否默认密码 0：否、1：是")
    private String isDefaultPwd;



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
     * 企业id
     */
    public String getCorpId() {
        return corpId;
    }

    /**
     * 企业id
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 密码加密 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 密码加密 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 是否激活  0-失效 1-激活
     */
    public String getActive() {
        return active;
    }

    /**
     * 是否激活  0-失效 1-激活
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     * 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 头像id
     */
    public String getPhotoId() {
        return photoId;
    }

    /**
     * 头像id
     */
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    /**
     * 工号
     */
    public String getJobNo() {
        return jobNo;
    }

    /**
     * 工号
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
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
     * 用户类型 1:超级管理员 2：项目负责人 3：精益办成员 4：专家
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 用户类型 1:超级管理员 2：项目负责人 3：精益办成员 4：专家
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 部门id
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * 部门id
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 联系方式，手机、固定电话、邮箱等等
     */
    public String getContact() {
        return contact;
    }

    /**
     * 联系方式，手机、固定电话、邮箱等等
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 删除标志 0：未删除、1：已删除
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标志 0：未删除、1：已删除
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 删除时间
     */
    public Date getDelTs() {
        return delTs;
    }

    /**
     * 删除时间
     */
    public void setDelTs(Date delTs) {
        this.delTs = delTs;
    }

    /**
     * 删除者
     */
    public String getDelUserId() {
        return delUserId;
    }

    /**
     * 删除者
     */
    public void setDelUserId(String delUserId) {
        this.delUserId = delUserId;
    }

    /**
     * 最后登录时间
     */
    public Date getLastLoginTs() {
        return lastLoginTs;
    }

    /**
     * 最后登录时间
     */
    public void setLastLoginTs(Date lastLoginTs) {
        this.lastLoginTs = lastLoginTs;
    }

    /**
     * 最后登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 最后登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 是否默认密码 0：否、1：是
     */
    public String getIsDefaultPwd() {
        return isDefaultPwd;
    }

    /**
     * 是否默认密码 0：否、1：是
     */
    public void setIsDefaultPwd(String isDefaultPwd) {
        this.isDefaultPwd = isDefaultPwd;
    }

}