package com.jacquinc.admin.sys.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.base.utils.MapUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import com.jacquinc.admin.sys.entity.PermissionEntity;
import com.jacquinc.admin.sys.entity.PermissionEntityCondition;
import com.jacquinc.admin.sys.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiezm
 */
@Service
@Transactional
public class PermissionService extends BaseServiceImpl implements IPermissionService {

    private static final String MAPPER_NAMESPACE = "com.jacquinc.admin.sys.sqlmapper.PermissionMapper";

    @Autowired
    IRolePermissionService rolePermissionService;

    @Override
    public List<String> findPermissionCodeListByRoleIds(List<String> roleIds) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("roleIds", roleIds);
        return this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findPermissionCodeListByRoleIds", params);
    }


    @Override
    public List<PermissionVO> findByMenuId(String menuId) {
        PermissionEntityCondition condition = new PermissionEntityCondition();
        condition.createCriteria().andMenuIdEqualTo(menuId);
        List<PermissionEntity> entityList = this.selectByCondition(condition);
        return BeanUtils.copyToNewList(entityList, PermissionVO.class);
    }

    @Override
    public ResponseResult delete(String id) {
        if (rolePermissionService.countByPermissionId(id) > 0) {
            return new ResponseResult(false, null, "删除失败！权限已经分配使用中，无法修改！");
        }

        PermissionEntityCondition condition = new PermissionEntityCondition();
        condition.createCriteria().andIdEqualTo(id);
        if (this.deleteByCondition(condition) > 0) {
            return new ResponseResult("删除成功！");
        }
        return new ResponseResult(false, null, "删除失败！");
    }

    @Override
    public int deleteByMenuId(String menuId) {
        PermissionEntityCondition condition = new PermissionEntityCondition();
        condition.createCriteria().andMenuIdEqualTo(menuId);
        return this.deleteByCondition(condition);
    }

    @Override
    public Set<String> findIdsByMenuId(String menuId) {
        String sql = "SELECT id FROM t_jj_sys_permission WHERE menu_id = #{menuId}";
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("menuId", menuId);
        Map<String, String> map = this.getDao().selectMapByDynamicSql(sql, parameterMap, "id");
        return map.keySet();
    }

    @Override
    public ResponseResult saveList(String menuId, List<PermissionVO> permissions) {
        List<PermissionVO> addVOList = permissions.stream().filter(item -> StringUtils.isEmpty(item.getId())).collect(Collectors.toList());
        List<PermissionVO> modifyVOList = Lists.newArrayList(permissions);
        modifyVOList.removeAll(addVOList);
        //新增
        for (PermissionVO addEntity : addVOList) {
            addEntity.setMenuId(menuId);
        }
        this.insertList(BeanUtils.copyToNewList(addVOList, PermissionEntity.class));
        //修改
        this.update(BeanUtils.copyToNewList(modifyVOList, PermissionEntity.class));
        //删除
        return new ResponseResult(true);
    }

    @Override
    public ResponseResult countByPermissionIds(Set<String> permissionIds) {
        StringBuilder existBuilder = new StringBuilder();
        String sql = "SELECT t1.name,t1.code FROM t_jj_sys_permission t1, t_jj_sys_role_permission t2 WHERE t1.id = t2.permission_id and t1.id=#{id}";
        for (String permissionId : permissionIds) {
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("id", permissionId);
            Map<String, Object> map = this.getDao().selectOneByDynamicSql(sql, parameterMap);
            PermissionEntity entity = MapUtils.mapToBean(map, PermissionEntity.class);
            if (entity != null) {
                existBuilder.append(entity.getCode());
                existBuilder.append(',');
            }
        }
        if (StringUtils.isNotEmpty(existBuilder)) {
            existBuilder.append(" 已经配置使用中");
            return new ResponseResult(existBuilder.toString(), false);
        } else {
            return new ResponseResult(true);
        }
    }

}
