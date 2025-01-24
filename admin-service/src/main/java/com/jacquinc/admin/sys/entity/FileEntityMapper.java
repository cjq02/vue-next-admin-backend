package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.FileEntity;
import com.jacquinc.admin.sys.entity.FileEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileEntityMapper extends BaseMapper {
    long countByCondition(FileEntityCondition example);

    int deleteByCondition(FileEntityCondition example);

    List<FileEntity> selectByCondition(FileEntityCondition example);

    int updateByCondition(@Param("record") FileEntity record, @Param("example") FileEntityCondition example);
}