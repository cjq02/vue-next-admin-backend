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
@Table(name = "t_jj_sys_user")
public class UserEntity extends BaseEntity implements Serializable {
    /** 企业id */
    @Column(length = 32)
    private String corpId;

    /** 用户名 */
    @Column(length = 50)
    private String userName;

    /** 密码 */
    @Column(length = 64)
    private String password;

    /** 真实姓名 */
    @Column(length = 50)
    private String realName;

    /** 密码加密 盐 */
    @Column(length = 32)
    private String salt;

    /** 是否激活  0-失效 1-激活 */
    @Column(length = 1)
    private String active;

    /** 手机号 */
    @Column(length = 30)
    private String phone;

    /** 邮箱 */
    @Column(length = 128)
    private String email;

    /** 头像id */
    @Column(length = 32)
    private String photoId;

    /** 工号 */
    @Column(length = 50)
    private String jobNo;

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

    /** 用户类型 1:超级管理员 2：项目负责人 3：精益办成员 4：专家 */
    @Column(length = 1)
    private String userType;

    /** 部门id */
    @Column(length = 32)
    private String departmentId;

    /** 联系方式，手机、固定电话、邮箱等等 */
    @Column(length = 50)
    private String contact;

    /** 删除标志 0：未删除、1：已删除 */
    @Column(length = 1)
    private String delFlag;

    /** 删除时间 */
    @Column(length = 29)
    private Date delTs;

    /** 删除者 */
    @Column(length = 32)
    private String delUserId;

    /** 最后登录时间 */
    @Column(length = 29)
    private Date lastLoginTs;

    /** 最后登录IP */
    @Column(length = 100)
    private String lastLoginIp;

    /** 是否默认密码 0：否、1：是 */
    @Column(length = 1)
    private String isDefaultPwd;

    private static final long serialVersionUID = 1L;

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
     * 用户名
     * @return userName
     */
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
        addSettedField("userName");
    }

    /**
     * 密码
     * @return password
     */
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
        addSettedField("password");
    }

    /**
     * 真实姓名
     * @return realName
     */
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    /**
     * 真实姓名
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
        addSettedField("realName");
    }

    /**
     * 密码加密 盐
     * @return salt
     */
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    /**
     * 密码加密 盐
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
        addSettedField("salt");
    }

    /**
     * 是否激活  0-失效 1-激活
     * @return active
     */
    @Column(name = "active")
    public String getActive() {
        return active;
    }

    /**
     * 是否激活  0-失效 1-激活
     * @param active
     */
    public void setActive(String active) {
        this.active = active;
        addSettedField("active");
    }

    /**
     * 手机号
     * @return phone
     */
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
        addSettedField("phone");
    }

    /**
     * 邮箱
     * @return email
     */
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
        addSettedField("email");
    }

    /**
     * 头像id
     * @return photoId
     */
    @Column(name = "photo_id")
    public String getPhotoId() {
        return photoId;
    }

    /**
     * 头像id
     * @param photoId
     */
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
        addSettedField("photoId");
    }

    /**
     * 工号
     * @return jobNo
     */
    @Column(name = "job_no")
    public String getJobNo() {
        return jobNo;
    }

    /**
     * 工号
     * @param jobNo
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
        addSettedField("jobNo");
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
     * 用户类型 1:超级管理员 2：项目负责人 3：精益办成员 4：专家
     * @return userType
     */
    @Column(name = "user_type")
    public String getUserType() {
        return userType;
    }

    /**
     * 用户类型 1:超级管理员 2：项目负责人 3：精益办成员 4：专家
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
        addSettedField("userType");
    }

    /**
     * 部门id
     * @return departmentId
     */
    @Column(name = "department_id")
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * 部门id
     * @param departmentId
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
        addSettedField("departmentId");
    }

    /**
     * 联系方式，手机、固定电话、邮箱等等
     * @return contact
     */
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    /**
     * 联系方式，手机、固定电话、邮箱等等
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
        addSettedField("contact");
    }

    /**
     * 删除标志 0：未删除、1：已删除
     * @return delFlag
     */
    @Column(name = "del_flag")
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标志 0：未删除、1：已删除
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        addSettedField("delFlag");
    }

    /**
     * 删除时间
     * @return delTs
     */
    @Column(name = "del_ts")
    public Date getDelTs() {
        return delTs;
    }

    /**
     * 删除时间
     * @param delTs
     */
    public void setDelTs(Date delTs) {
        this.delTs = delTs;
        addSettedField("delTs");
    }

    /**
     * 删除者
     * @return delUserId
     */
    @Column(name = "del_user_id")
    public String getDelUserId() {
        return delUserId;
    }

    /**
     * 删除者
     * @param delUserId
     */
    public void setDelUserId(String delUserId) {
        this.delUserId = delUserId;
        addSettedField("delUserId");
    }

    /**
     * 最后登录时间
     * @return lastLoginTs
     */
    @Column(name = "last_login_ts")
    public Date getLastLoginTs() {
        return lastLoginTs;
    }

    /**
     * 最后登录时间
     * @param lastLoginTs
     */
    public void setLastLoginTs(Date lastLoginTs) {
        this.lastLoginTs = lastLoginTs;
        addSettedField("lastLoginTs");
    }

    /**
     * 最后登录IP
     * @return lastLoginIp
     */
    @Column(name = "last_login_ip")
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 最后登录IP
     * @param lastLoginIp
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
        addSettedField("lastLoginIp");
    }

    /**
     * 是否默认密码 0：否、1：是
     * @return isDefaultPwd
     */
    @Column(name = "is_default_pwd")
    public String getIsDefaultPwd() {
        return isDefaultPwd;
    }

    /**
     * 是否默认密码 0：否、1：是
     * @param isDefaultPwd
     */
    public void setIsDefaultPwd(String isDefaultPwd) {
        this.isDefaultPwd = isDefaultPwd;
        addSettedField("isDefaultPwd");
    }

    /**
     * 获得当前实体 Mapper Class
     * @return Class
     */
    @Override
    public Class<?> obtainEntityMapperClass() {
        return UserEntityMapper.class;
    }
}