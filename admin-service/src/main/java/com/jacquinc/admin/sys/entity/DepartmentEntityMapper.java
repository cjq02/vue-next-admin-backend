package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.DepartmentEntity;
import com.jacquinc.admin.sys.entity.DepartmentEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentEntityMapper extends BaseMapper {
    long countByCondition(DepartmentEntityCondition example);

    int deleteByCondition(DepartmentEntityCondition example);

    List<DepartmentEntity> selectByCondition(DepartmentEntityCondition example);

    int updateByCondition(@Param("record") DepartmentEntity record, @Param("example") DepartmentEntityCondition example);
}