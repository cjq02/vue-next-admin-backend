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
@Table(name = "t_jj_sys_file")
public class FileEntity extends BaseEntity implements Serializable {
    /** 文件名称 */
    @Column(length = 100)
    private String fileName;

    /** 文件类型 */
    @Column(length = 20)
    private String fileType;

    /** 文件路径 */
    @Column(length = 300)
    private String fileUrl;

    /** 文件大小 */
    @Column(length = 19)
    private Long fileSize;

    /** oss_key */
    @Column(length = 255)
    private String ossKey;

    /** 创建时间 */
    @Column(length = 22)
    private Date createTs;

    /** 创建者 */
    @Column(length = 32)
    private String createUserId;

    /**  */
    @Column(length = 22)
    private Date updateTs;

    /**  */
    @Column(length = 32)
    private String updateUserId;

    /**  */
    @Column(length = 100)
    private String hashCode;

    /** 业务ID */
    @Column(length = 32)
    private String bizId;

    /** 业务类型 */
    @Column(length = 4)
    private String bizType;

    /** 子业务类型 */
    @Column(length = 10)
    private String bizDetailType;

    /** 缩略图文件路经 */
    @Column(length = 300)
    private String thumbnailFileUrl;

    /** 缩略图文件大小 */
    @Column(length = 19)
    private Long thumbnailFileSize;

    /** 缩略图hashCode */
    @Column(length = 100)
    private String thumbnailHashCode;

    /**  */
    @Column(length = 256)
    private String parameter1;

    /**  */
    @Column(length = 256)
    private String parameter2;

    /**  */
    @Column(length = 256)
    private String parameter3;

    /** 更新者 */
    @Column(length = 10)
    private Integer seqNo;

    /** 备注 */
    @Column(length = 500)
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 文件名称
     * @return fileName
     */
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名称
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
        addSettedField("fileName");
    }

    /**
     * 文件类型
     * @return fileType
     */
    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    /**
     * 文件类型
     * @param fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
        addSettedField("fileType");
    }

    /**
     * 文件路径
     * @return fileUrl
     */
    @Column(name = "file_url")
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 文件路径
     * @param fileUrl
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        addSettedField("fileUrl");
    }

    /**
     * 文件大小
     * @return fileSize
     */
    @Column(name = "file_size")
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 文件大小
     * @param fileSize
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
        addSettedField("fileSize");
    }

    /**
     * oss_key
     * @return ossKey
     */
    @Column(name = "oss_key")
    public String getOssKey() {
        return ossKey;
    }

    /**
     * oss_key
     * @param ossKey
     */
    public void setOssKey(String ossKey) {
        this.ossKey = ossKey;
        addSettedField("ossKey");
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
     * 
     * @return updateTs
     */
    @Column(name = "update_ts")
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * 
     * @param updateTs
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
        addSettedField("updateTs");
    }

    /**
     * 
     * @return updateUserId
     */
    @Column(name = "update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 
     * @param updateUserId
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        addSettedField("updateUserId");
    }

    /**
     * 
     * @return hashCode
     */
    @Column(name = "hash_code")
    public String getHashCode() {
        return hashCode;
    }

    /**
     * 
     * @param hashCode
     */
    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
        addSettedField("hashCode");
    }

    /**
     * 业务ID
     * @return bizId
     */
    @Column(name = "biz_id")
    public String getBizId() {
        return bizId;
    }

    /**
     * 业务ID
     * @param bizId
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
        addSettedField("bizId");
    }

    /**
     * 业务类型
     * @return bizType
     */
    @Column(name = "biz_type")
    public String getBizType() {
        return bizType;
    }

    /**
     * 业务类型
     * @param bizType
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
        addSettedField("bizType");
    }

    /**
     * 子业务类型
     * @return bizDetailType
     */
    @Column(name = "biz_detail_type")
    public String getBizDetailType() {
        return bizDetailType;
    }

    /**
     * 子业务类型
     * @param bizDetailType
     */
    public void setBizDetailType(String bizDetailType) {
        this.bizDetailType = bizDetailType;
        addSettedField("bizDetailType");
    }

    /**
     * 缩略图文件路经
     * @return thumbnailFileUrl
     */
    @Column(name = "thumbnail_file_url")
    public String getThumbnailFileUrl() {
        return thumbnailFileUrl;
    }

    /**
     * 缩略图文件路经
     * @param thumbnailFileUrl
     */
    public void setThumbnailFileUrl(String thumbnailFileUrl) {
        this.thumbnailFileUrl = thumbnailFileUrl;
        addSettedField("thumbnailFileUrl");
    }

    /**
     * 缩略图文件大小
     * @return thumbnailFileSize
     */
    @Column(name = "thumbnail_file_size")
    public Long getThumbnailFileSize() {
        return thumbnailFileSize;
    }

    /**
     * 缩略图文件大小
     * @param thumbnailFileSize
     */
    public void setThumbnailFileSize(Long thumbnailFileSize) {
        this.thumbnailFileSize = thumbnailFileSize;
        addSettedField("thumbnailFileSize");
    }

    /**
     * 缩略图hashCode
     * @return thumbnailHashCode
     */
    @Column(name = "thumbnail_hash_code")
    public String getThumbnailHashCode() {
        return thumbnailHashCode;
    }

    /**
     * 缩略图hashCode
     * @param thumbnailHashCode
     */
    public void setThumbnailHashCode(String thumbnailHashCode) {
        this.thumbnailHashCode = thumbnailHashCode;
        addSettedField("thumbnailHashCode");
    }

    /**
     * 
     * @return parameter1
     */
    @Column(name = "parameter1")
    public String getParameter1() {
        return parameter1;
    }

    /**
     * 
     * @param parameter1
     */
    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
        addSettedField("parameter1");
    }

    /**
     * 
     * @return parameter2
     */
    @Column(name = "parameter2")
    public String getParameter2() {
        return parameter2;
    }

    /**
     * 
     * @param parameter2
     */
    public void setParameter2(String parameter2) {
        this.parameter2 = parameter2;
        addSettedField("parameter2");
    }

    /**
     * 
     * @return parameter3
     */
    @Column(name = "parameter3")
    public String getParameter3() {
        return parameter3;
    }

    /**
     * 
     * @param parameter3
     */
    public void setParameter3(String parameter3) {
        this.parameter3 = parameter3;
        addSettedField("parameter3");
    }

    /**
     * 更新者
     * @return seqNo
     */
    @Column(name = "seq_no")
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * 更新者
     * @param seqNo
     */
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
        addSettedField("seqNo");
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
     * 获得当前实体 Mapper Class
     * @return Class
     */
    @Override
    public Class<?> obtainEntityMapperClass() {
        return FileEntityMapper.class;
    }
}