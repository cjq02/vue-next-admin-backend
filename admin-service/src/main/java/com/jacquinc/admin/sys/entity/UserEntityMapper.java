package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.UserEntity;
import com.jacquinc.admin.sys.entity.UserEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserEntityMapper extends BaseMapper {
    long countByCondition(UserEntityCondition example);

    int deleteByCondition(UserEntityCondition example);

    List<UserEntity> selectByCondition(UserEntityCondition example);

    int updateByCondition(@Param("record") UserEntity record, @Param("example") UserEntityCondition example);
}