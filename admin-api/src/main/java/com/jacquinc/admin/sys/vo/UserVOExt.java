package com.jacquinc.admin.sys.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserVOExt extends UserVO {

    /**
     * 查询使用
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色ID列表
     */
    private List<String> roleIds;

    /**
     * 角色名称列表
     */
    private List<String> roleNames;

    /**
     * 角色类型列表
     */
    private List<String> roleTypes;

    /**
     * 企业信息
     */
    private CorpVOExt corpInfo;

    /**
     * 企业名称
     */
    private String corpName;

    /**
     * 权限
     */
    private List<String> permissions;

    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 排除超管
     */
    private String excludeSuperAdmin;

    public String getDeptId() {
        return getDepartmentId();
    }
}
