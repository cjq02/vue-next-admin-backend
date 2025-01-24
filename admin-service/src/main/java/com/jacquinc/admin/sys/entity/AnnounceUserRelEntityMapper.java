package com.jacquinc.admin.sys.entity;

import com.jiujie.framework.mybatis.mapper.BaseMapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnounceUserRelEntityMapper extends BaseMapper {
    long countByCondition(AnnounceUserRelEntityCondition example);

    int deleteByCondition(AnnounceUserRelEntityCondition example);

    List<AnnounceUserRelEntity> selectByCondition(AnnounceUserRelEntityCondition example);

    int updateByCondition(@Param("record") AnnounceUserRelEntity record, @Param("example") AnnounceUserRelEntityCondition example);
}