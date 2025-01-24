package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class FileVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "文件路径")
    private String fileUrl;

    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    @ApiModelProperty(value = "oss_key")
    private String ossKey;

    @ApiModelProperty(value = "创建时间")
    private Date createTs;

    @ApiModelProperty(value = "创建者")
    private String createUserId;

    @ApiModelProperty(value = "")
    private Date updateTs;

    @ApiModelProperty(value = "")
    private String updateUserId;

    @ApiModelProperty(value = "")
    private String hashCode;

    @ApiModelProperty(value = "业务ID")
    private String bizId;

    @ApiModelProperty(value = "业务类型")
    private String bizType;

    @ApiModelProperty(value = "子业务类型")
    private String bizDetailType;

    @ApiModelProperty(value = "缩略图文件路经")
    private String thumbnailFileUrl;

    @ApiModelProperty(value = "缩略图文件大小")
    private Long thumbnailFileSize;

    @ApiModelProperty(value = "缩略图hashCode")
    private String thumbnailHashCode;

    @ApiModelProperty(value = "")
    private String parameter1;

    @ApiModelProperty(value = "")
    private String parameter2;

    @ApiModelProperty(value = "")
    private String parameter3;

    @ApiModelProperty(value = "更新者")
    private Integer seqNo;

    @ApiModelProperty(value = "备注")
    private String remark;



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
     * 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 文件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 文件路径
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 文件路径
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * 文件大小
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 文件大小
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * oss_key
     */
    public String getOssKey() {
        return ossKey;
    }

    /**
     * oss_key
     */
    public void setOssKey(String ossKey) {
        this.ossKey = ossKey;
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
     * 
     */
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * 
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    /**
     * 
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 
     */
    public String getHashCode() {
        return hashCode;
    }

    /**
     * 
     */
    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    /**
     * 业务ID
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * 业务ID
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * 业务类型
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * 业务类型
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    /**
     * 子业务类型
     */
    public String getBizDetailType() {
        return bizDetailType;
    }

    /**
     * 子业务类型
     */
    public void setBizDetailType(String bizDetailType) {
        this.bizDetailType = bizDetailType;
    }

    /**
     * 缩略图文件路经
     */
    public String getThumbnailFileUrl() {
        return thumbnailFileUrl;
    }

    /**
     * 缩略图文件路经
     */
    public void setThumbnailFileUrl(String thumbnailFileUrl) {
        this.thumbnailFileUrl = thumbnailFileUrl;
    }

    /**
     * 缩略图文件大小
     */
    public Long getThumbnailFileSize() {
        return thumbnailFileSize;
    }

    /**
     * 缩略图文件大小
     */
    public void setThumbnailFileSize(Long thumbnailFileSize) {
        this.thumbnailFileSize = thumbnailFileSize;
    }

    /**
     * 缩略图hashCode
     */
    public String getThumbnailHashCode() {
        return thumbnailHashCode;
    }

    /**
     * 缩略图hashCode
     */
    public void setThumbnailHashCode(String thumbnailHashCode) {
        this.thumbnailHashCode = thumbnailHashCode;
    }

    /**
     * 
     */
    public String getParameter1() {
        return parameter1;
    }

    /**
     * 
     */
    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
    }

    /**
     * 
     */
    public String getParameter2() {
        return parameter2;
    }

    /**
     * 
     */
    public void setParameter2(String parameter2) {
        this.parameter2 = parameter2;
    }

    /**
     * 
     */
    public String getParameter3() {
        return parameter3;
    }

    /**
     * 
     */
    public void setParameter3(String parameter3) {
        this.parameter3 = parameter3;
    }

    /**
     * 更新者
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * 更新者
     */
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
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

}