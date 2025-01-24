package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class MenuVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "链接")
    private String url;

    @ApiModelProperty(value = "父Id")
    private String parentId;

    @ApiModelProperty(value = "权限前缀码")
    private String permissionPrefixCode;

    @ApiModelProperty(value = "创建人")
    private String createUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTs;

    @ApiModelProperty(value = "更新人")
    private String updateUserId;

    @ApiModelProperty(value = "更新时间")
    private Date updateTs;

    @ApiModelProperty(value = "菜单权限： 1：超管 2：服务商 3：企业用户 4：有服务商的企业用户 5：政策服务商，多个用逗号隔开")
    private String isAdmin;

    @ApiModelProperty(value = "是否展示在服务商工作台")
    private String isPartner;



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
     * 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * 优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 父Id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父Id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 权限前缀码
     */
    public String getPermissionPrefixCode() {
        return permissionPrefixCode;
    }

    /**
     * 权限前缀码
     */
    public void setPermissionPrefixCode(String permissionPrefixCode) {
        this.permissionPrefixCode = permissionPrefixCode;
    }

    /**
     * 创建人
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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
     * 更新人
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
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
     * 菜单权限： 1：超管 2：服务商 3：企业用户 4：有服务商的企业用户 5：政策服务商，多个用逗号隔开
     */
    public String getIsAdmin() {
        return isAdmin;
    }

    /**
     * 菜单权限： 1：超管 2：服务商 3：企业用户 4：有服务商的企业用户 5：政策服务商，多个用逗号隔开
     */
    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * 是否展示在服务商工作台
     */
    public String getIsPartner() {
        return isPartner;
    }

    /**
     * 是否展示在服务商工作台
     */
    public void setIsPartner(String isPartner) {
        this.isPartner = isPartner;
    }

}