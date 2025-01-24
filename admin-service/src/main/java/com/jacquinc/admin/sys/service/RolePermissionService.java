package com.jacquinc.admin.sys.service;

import com.jacquinc.admin.sys.entity.RolePermissionEntity;
import com.jiujie.framework.base.utils.UUIDUtils;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import com.jacquinc.admin.sys.entity.RolePermissionEntityCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiezm
 */
@Service
@Transactional
public class RolePermissionService extends BaseServiceImpl implements IRolePermissionService {

    private static final String MAPPER_NAMESPACE = "com.jacquinc.admin.sys.sqlmapper.RolePermissionMapper";

    @Override
    public List<String> findPermissionIdsByRoleId(String roleId) {
        RolePermissionEntityCondition condition = new RolePermissionEntityCondition();
        condition.createCriteria().andRoleIdEqualTo(roleId);
        List<RolePermissionEntity> list = this.getDao().selectByCondition(condition);
        return list.stream().map(RolePermissionEntity::getPermissionId).collect(Collectors.toList());
    }

    @Override
    public int deleteByPermissionIds(List<String> permissionIds) {
        if (!permissionIds.isEmpty()) {
            RolePermissionEntityCondition condition = new RolePermissionEntityCondition();
            condition.createCriteria().andPermissionIdIn(permissionIds);
            return this.deleteByCondition(condition);
        } else {
            return 0;
        }
    }

    @Override
    public int deleteByRoleId(String roleId) {
        RolePermissionEntityCondition rolePermissionEntityCondition = new RolePermissionEntityCondition();
        rolePermissionEntityCondition.createCriteria().andRoleIdEqualTo(roleId);
        return this.deleteByCondition(rolePermissionEntityCondition);
    }

    @Override
    public int batchAdd(List<String> permissionIds, String roleId) {
        //批量新增
        if (!permissionIds.isEmpty()) {
            List<RolePermissionEntity> permissionEntities = new ArrayList<>();
            for (String permission : permissionIds) {
                RolePermissionEntity permissionEntity = new RolePermissionEntity();
                permissionEntity.setId(UUIDUtils.getStringValue());
                permissionEntity.setPermissionId(permission);
                permissionEntity.setRoleId(roleId);
                permissionEntities.add(permissionEntity);
            }
            return this.insertList(permissionEntities);
        } else {
            return 0;
        }
    }

    @Override
    public long countByPermissionId(String permissionId) {
        RolePermissionEntityCondition condition = new RolePermissionEntityCondition();
        condition.createCriteria().andPermissionIdEqualTo(permissionId);
        return this.countByCondition(condition);
    }

}
