package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.PermissionEntity;
import com.jacquinc.admin.sys.entity.PermissionEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionEntityMapper extends BaseMapper {
    long countByCondition(PermissionEntityCondition example);

    int deleteByCondition(PermissionEntityCondition example);

    List<PermissionEntity> selectByCondition(PermissionEntityCondition example);

    int updateByCondition(@Param("record") PermissionEntity record, @Param("example") PermissionEntityCondition example);
}