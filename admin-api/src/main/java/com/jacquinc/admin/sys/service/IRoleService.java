package com.jacquinc.admin.sys.service;

import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.vo.RoleVO;
import com.jacquinc.admin.sys.vo.RoleVOExt;
import com.jiujie.framework.adapter.vo.ResponseResult;

import java.util.List;

/**
 * @author xiezm
 */
public interface IRoleService {

    /**
     * 角色页面
     *
     * @param page      页面对象
     * @return 页面对象
     */
    Page<RoleVOExt, RoleVOExt> getRolePage(Page<RoleVOExt, RoleVOExt> page);

    /**
     * 获取列表
     *
     * @param condition 查询字段
     * @return 列表
     */
    List<RoleVOExt> findList(RoleVOExt condition);

    /**
     * 获取下拉列表
     *
     * @param inputValue 查询字段
     * @param roleTypes 角色类型集合
     * @return 下拉列表
     */
    List<RoleVOExt> findSelectList(String inputValue, List<String> roleTypes);

    /**
     * 保存
     *
     * @param roleVO 角色对象
     * @param userId 相当用户
     * @return 响应结果
     */
    ResponseResult save(RoleVOExt roleVO, String userId);

    ResponseResult delete(String id);

    List<RoleVOExt> findByCorpId(String corpId);

    RoleVO getRoleById(String roleId);

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return 列表
     */
    List<RoleVOExt> findListByUserId(String userId);

    /**
     * 根据角色类型获取角色
     *
     * @param roleType 角色类型
     * @return 角色对象
     */
    RoleVOExt getRoleByType(String roleType);

}
