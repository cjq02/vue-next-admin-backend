package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.RoleEntity;
import com.jacquinc.admin.sys.entity.RoleEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleEntityMapper extends BaseMapper {
    long countByCondition(RoleEntityCondition example);

    int deleteByCondition(RoleEntityCondition example);

    List<RoleEntity> selectByCondition(RoleEntityCondition example);

    int updateByCondition(@Param("record") RoleEntity record, @Param("example") RoleEntityCondition example);
}