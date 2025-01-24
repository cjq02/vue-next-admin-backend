package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.CodeEntity;
import com.jacquinc.admin.sys.entity.CodeEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeEntityMapper extends BaseMapper {
    long countByCondition(CodeEntityCondition example);

    int deleteByCondition(CodeEntityCondition example);

    List<CodeEntity> selectByCondition(CodeEntityCondition example);

    int updateByCondition(@Param("record") CodeEntity record, @Param("example") CodeEntityCondition example);
}