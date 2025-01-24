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
@Table(name = "t_jj_sys_announce")
public class AnnounceEntity extends BaseEntity implements Serializable {
    /** 标题 */
    @Column(length = 200)
    private String title;

    /** 内容 */
    @Column(length = 2147483647)
    private String content;

    /** 状态 0-未发布 1-已发布 */
    @Column(length = 1)
    private String status;

    /** 备注 */
    @Column(length = 500)
    private String remark;

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

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     * @return title
     */
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
        addSettedField("title");
    }

    /**
     * 内容
     * @return content
     */
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
        addSettedField("content");
    }

    /**
     * 状态 0-未发布 1-已发布
     * @return status
     */
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    /**
     * 状态 0-未发布 1-已发布
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
        addSettedField("status");
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
     * 获得当前实体 Mapper Class
     * @return Class
     */
    @Override
    public Class<?> obtainEntityMapperClass() {
        return AnnounceEntityMapper.class;
    }
}