package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class AnnounceUserRelVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "公告id")
    private String announceId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "状态 0-未读 1-已读")
    private String status;

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
     * 公告id
     */
    public String getAnnounceId() {
        return announceId;
    }

    /**
     * 公告id
     */
    public void setAnnounceId(String announceId) {
        this.announceId = announceId;
    }

    /**
     * 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 状态 0-未读 1-已读
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态 0-未读 1-已读
     */
    public void setStatus(String status) {
        this.status = status;
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