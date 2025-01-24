package com.jacquinc.admin.sys.entity;

import com.jiujie.framework.mybatis.mapper.BaseMapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnounceEntityMapper extends BaseMapper {
    long countByCondition(AnnounceEntityCondition example);

    int deleteByCondition(AnnounceEntityCondition example);

    List<AnnounceEntity> selectByCondition(AnnounceEntityCondition example);

    int updateByCondition(@Param("record") AnnounceEntity record, @Param("example") AnnounceEntityCondition example);
}