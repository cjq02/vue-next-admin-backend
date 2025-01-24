package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.UserRoleEntity;
import com.jacquinc.admin.sys.entity.UserRoleEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleEntityMapper extends BaseMapper {
    long countByCondition(UserRoleEntityCondition example);

    int deleteByCondition(UserRoleEntityCondition example);

    List<UserRoleEntity> selectByCondition(UserRoleEntityCondition example);

    int updateByCondition(@Param("record") UserRoleEntity record, @Param("example") UserRoleEntityCondition example);
}