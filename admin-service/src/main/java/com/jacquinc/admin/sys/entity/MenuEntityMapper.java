package com.jacquinc.admin.sys.entity;

import com.jacquinc.admin.sys.entity.MenuEntity;
import com.jacquinc.admin.sys.entity.MenuEntityCondition;
import com.jiujie.framework.mybatis.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuEntityMapper extends BaseMapper {
    long countByCondition(MenuEntityCondition example);

    int deleteByCondition(MenuEntityCondition example);

    List<MenuEntity> selectByCondition(MenuEntityCondition example);

    int updateByCondition(@Param("record") MenuEntity record, @Param("example") MenuEntityCondition example);
}