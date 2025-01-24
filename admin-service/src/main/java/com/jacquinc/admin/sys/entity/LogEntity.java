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
@Table(name = "t_jj_sys_log")
public class LogEntity extends BaseEntity implements Serializable {
    /** 路径 */
    @Column(length = 512)
    private String url;

    /** 请求参数 */
    @Column(length = 2147483647)
    private String parameter;

    /** 客户ip地址 */
    @Column(length = 100)
    private String ip;

    /** 异常状态 0：无异常 1：异常 */
    @Column(length = 1)
    private String status;

    /** 异常内容 */
    @Column(length = 2147483647)
    private String exception;

    /** 备注 */
    @Column(length = 1024)
    private String remark;

    /** 创建时间 */
    @Column(length = 22)
    private Date createTs;

    /** 创建者 */
    @Column(length = 32)
    private String createUserId;

    private static final long serialVersionUID = 1L;

    /**
     * 路径
     * @return url
     */
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    /**
     * 路径
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
        addSettedField("url");
    }

    /**
     * 请求参数
     * @return parameter
     */
    @Column(name = "parameter")
    public String getParameter() {
        return parameter;
    }

    /**
     * 请求参数
     * @param parameter
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
        addSettedField("parameter");
    }

    /**
     * 客户ip地址
     * @return ip
     */
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    /**
     * 客户ip地址
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
        addSettedField("ip");
    }

    /**
     * 异常状态 0：无异常 1：异常
     * @return status
     */
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    /**
     * 异常状态 0：无异常 1：异常
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
        addSettedField("status");
    }

    /**
     * 异常内容
     * @return exception
     */
    @Column(name = "exception")
    public String getException() {
        return exception;
    }

    /**
     * 异常内容
     * @param exception
     */
    public void setException(String exception) {
        this.exception = exception;
        addSettedField("exception");
    }

    /**
     * 备注
     * @return remark
     */
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
        addSettedField("remark");
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
     * 获得当前实体 Mapper Class
     * @return Class
     */
    @Override
    public Class<?> obtainEntityMapperClass() {
        return LogEntityMapper.class;
    }
}