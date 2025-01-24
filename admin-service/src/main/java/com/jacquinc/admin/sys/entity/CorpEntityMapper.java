package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.CorpEntity;
import com.jacquinc.admin.sys.entity.CorpEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CorpEntityMapper extends BaseMapper {
    long countByCondition(CorpEntityCondition example);

    int deleteByCondition(CorpEntityCondition example);

    List<CorpEntity> selectByCondition(CorpEntityCondition example);

    int updateByCondition(@Param("record") CorpEntity record, @Param("example") CorpEntityCondition example);
}