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
@Table(name = "t_jj_sys_announce_user_rel")
public class AnnounceUserRelEntity extends BaseEntity implements Serializable {
    /** 公告id */
    @Column(length = 32)
    private String announceId;

    /** 用户id */
    @Column(length = 32)
    private String userId;

    /** 状态 0-未读 1-已读 */
    @Column(length = 1)
    private String status;

    /** 创建时间 */
    @Column(length = 22)
    private Date createTs;

    /** 创建者 */
    @Column(length = 32)
    private String createUserId;

    private static final long serialVersionUID = 1L;

    /**
     * 公告id
     * @return announceId
     */
    @Column(name = "announce_id")
    public String getAnnounceId() {
        return announceId;
    }

    /**
     * 公告id
     * @param announceId
     */
    public void setAnnounceId(String announceId) {
        this.announceId = announceId;
        addSettedField("announceId");
    }

    /**
     * 用户id
     * @return userId
     */
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
        addSettedField("userId");
    }

    /**
     * 状态 0-未读 1-已读
     * @return status
     */
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    /**
     * 状态 0-未读 1-已读
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
        addSettedField("status");
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
        return AnnounceUserRelEntityMapper.class;
    }
}