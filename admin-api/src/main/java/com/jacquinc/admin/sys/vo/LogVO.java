package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class LogVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "路径")
    private String url;

    @ApiModelProperty(value = "请求参数")
    private String parameter;

    @ApiModelProperty(value = "客户ip地址")
    private String ip;

    @ApiModelProperty(value = "异常状态 0：无异常 1：异常")
    private String status;

    @ApiModelProperty(value = "异常内容")
    private String exception;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTs;

    @ApiModelProperty(value = "创建者")
    private String createUserId;



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
     * 路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 请求参数
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * 请求参数
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * 客户ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 客户ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 异常状态 0：无异常 1：异常
     */
    public String getStatus() {
        return status;
    }

    /**
     * 异常状态 0：无异常 1：异常
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 异常内容
     */
    public String getException() {
        return exception;
    }

    /**
     * 异常内容
     */
    public void setException(String exception) {
        this.exception = exception;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

}