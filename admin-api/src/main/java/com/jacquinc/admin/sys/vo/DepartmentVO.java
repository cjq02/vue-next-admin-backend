package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class DepartmentVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "人员姓名")
    private String name;

    @ApiModelProperty(value = "企业id")
    private String corpId;

    @ApiModelProperty(value = "父级id")
    private String parentId;

    @ApiModelProperty(value = "创建时间")
    private Date createTs;

    @ApiModelProperty(value = "创建者")
    private String createUserId;

    @ApiModelProperty(value = "更新时间")
    private Date updateTs;

    @ApiModelProperty(value = "更新者")
    private String updateUserId;

    @ApiModelProperty(value = "部门编号")
    private String code;

    @ApiModelProperty(value = "部门别称")
    private String nickName;

    @ApiModelProperty(value = "部门类型 1：管理部门、2：监督检查部门、3：生产技术部门、4：洗选检验部门")
    private String type;



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
     * 人员姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 人员姓名
     */
    public void setName(String name) {
        this.name = name;
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
     * 父级id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父级id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
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
     * 部门编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 部门编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 部门别称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 部门别称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 部门类型 1：管理部门、2：监督检查部门、3：生产技术部门、4：洗选检验部门
     */
    public String getType() {
        return type;
    }

    /**
     * 部门类型 1：管理部门、2：监督检查部门、3：生产技术部门、4：洗选检验部门
     */
    public void setType(String type) {
        this.type = type;
    }

}