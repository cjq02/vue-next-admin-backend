package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class RoleVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "创建日期")
    private Date createTs;

    @ApiModelProperty(value = "创建人id")
    private String createUserId;

    @ApiModelProperty(value = "更新日期")
    private Date updateTs;

    @ApiModelProperty(value = "更新人id")
    private String updateUserId;

    @ApiModelProperty(value = "角色类型 01:超级管理员 02：普通管理员 09：普通用户")
    private String roleType;

    @ApiModelProperty(value = "")
    private String corpId;

    @ApiModelProperty(value = "工作流角色代号")
    private String workflowCode;



    /**
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 创建日期
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * 创建日期
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * 创建人id
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人id
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 更新日期
     */
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * 更新日期
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    /**
     * 更新人id
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人id
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 角色类型 01:超级管理员 02：普通管理员 09：普通用户
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * 角色类型 01:超级管理员 02：普通管理员 09：普通用户
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * 
     */
    public String getCorpId() {
        return corpId;
    }

    /**
     * 
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    /**
     * 工作流角色代号
     */
    public String getWorkflowCode() {
        return workflowCode;
    }

    /**
     * 工作流角色代号
     */
    public void setWorkflowCode(String workflowCode) {
        this.workflowCode = workflowCode;
    }

}