package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class UserRoleVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "角色Id")
    private String roleId;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "")
    private String createUserId;

    @ApiModelProperty(value = "")
    private Date createTs;

    @ApiModelProperty(value = "")
    private String updateUserId;

    @ApiModelProperty(value = "")
    private Date updateTs;



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
     * 角色Id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色Id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 用户Id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户Id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * 
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
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
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * 
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

}