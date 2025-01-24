package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.LogEntity;
import com.jacquinc.admin.sys.entity.LogEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogEntityMapper extends BaseMapper {
    long countByCondition(LogEntityCondition example);

    int deleteByCondition(LogEntityCondition example);

    List<LogEntity> selectByCondition(LogEntityCondition example);

    int updateByCondition(@Param("record") LogEntity record, @Param("example") LogEntityCondition example);
}