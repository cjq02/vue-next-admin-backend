package com.jacquinc.admin.sys.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author xiezm
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVOExt extends RoleVO {

    private List<String> permissionIds;

    private String userId;
}