package com.jacquinc.admin.sys.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author xiezm
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuVOExt extends MenuVO {

    private String parentName;


    private List<String> roleIds;
    private List<PermissionVO> permissions;

    private List<MenuVOExt> children = Lists.newArrayList();

    private String hasRoot;

    private String hasPermission;

    private String isSideMenu;

}
