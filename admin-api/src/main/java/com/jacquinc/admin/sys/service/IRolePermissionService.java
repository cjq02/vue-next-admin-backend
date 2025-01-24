package com.jacquinc.admin.sys.service;

import java.util.List;

/**
 * @author cjq
 * created on 2022/01/26
 */
public interface IRolePermissionService {

    /**
     * 根据角色ID获取权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    List<String> findPermissionIdsByRoleId(String roleId);

    int deleteByPermissionIds(List<String> permissionIds);

    int deleteByRoleId(String roleId);

    int batchAdd(List<String> permissionIds, String roleId);

    long countByPermissionId(String permissionId);
}
