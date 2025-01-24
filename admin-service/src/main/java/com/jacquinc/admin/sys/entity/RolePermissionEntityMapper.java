package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.RolePermissionEntity;
import com.jacquinc.admin.sys.entity.RolePermissionEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionEntityMapper extends BaseMapper {
    long countByCondition(RolePermissionEntityCondition example);

    int deleteByCondition(RolePermissionEntityCondition example);

    List<RolePermissionEntity> selectByCondition(RolePermissionEntityCondition example);

    int updateByCondition(@Param("record") RolePermissionEntity record, @Param("example") RolePermissionEntityCondition example);
}