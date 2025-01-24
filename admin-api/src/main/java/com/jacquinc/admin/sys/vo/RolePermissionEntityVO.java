package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/** 自动生成的VO,请不要修改 */
public class RolePermissionEntityVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "角色Id")
    private String roleId;

    @ApiModelProperty(value = "权限Id")
    private String permissionId;



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
     * 权限Id
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * 权限Id
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

}