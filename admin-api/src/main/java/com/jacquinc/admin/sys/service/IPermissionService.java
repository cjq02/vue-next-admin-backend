package com.jacquinc.admin.sys.service;

import com.jacquinc.admin.sys.vo.PermissionVO;
import com.jiujie.framework.adapter.vo.ResponseResult;

import java.util.List;
import java.util.Set;

/**
 * @author xiezm
 */
public interface IPermissionService {

    /**
     * 根据角色ID集合获取权限码集合
     *
     * @param roleIds 角色ID集合
     * @return 权限码集合
     */
    List<String> findPermissionCodeListByRoleIds(List<String> roleIds);


    List<PermissionVO> findByMenuId(String menuId);

    /**
     * 删除权限
     *
     * @param id 权限ID
     * @return 响应结果
     */
    ResponseResult delete(String id);

    int deleteByMenuId(String menuId);

    Set<String> findIdsByMenuId(String menuId);

    /**
     * 批量保存
     *
     * @param menuId 菜单ID
     * @param permissions 权限集合
     * @return 响应结果
     */
    ResponseResult saveList(String menuId, List<PermissionVO> permissions);

    ResponseResult countByPermissionIds(Set<String> permissionIds);
}
