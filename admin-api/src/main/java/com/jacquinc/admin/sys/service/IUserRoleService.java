package com.jacquinc.admin.sys.service;


import com.jacquinc.admin.sys.vo.UserRoleVO;

import java.util.List;

/**
 * @author xiezm
 */
public interface IUserRoleService {

    /**
     * 根据userId，找到已分配的角色
     *
     * @param userId 用户ID
     * @return 列表
     */
    List<UserRoleVO> findListByUserId(String userId);

    long countByRoleId(String roleId);

    int deleteByUserId(String userId);

    void add(UserRoleVO userRole);

    int batchAdd(List<String> roleIds, String userId);

}
