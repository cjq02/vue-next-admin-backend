package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/** 自动生成的VO,请不要修改 */
public class PermissionVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "权限 id，格式：菜单的sn:操作code")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "菜单Id")
    private String menuId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "")
    private String code;



    /**
     * 权限 id，格式：菜单的sn:操作code
     */
    public String getId() {
        return id;
    }

    /**
     * 权限 id，格式：菜单的sn:操作code
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
     * 菜单Id
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 菜单Id
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
     * 
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     */
    public void setCode(String code) {
        this.code = code;
    }

}