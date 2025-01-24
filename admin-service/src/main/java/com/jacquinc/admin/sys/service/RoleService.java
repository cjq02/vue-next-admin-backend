package com.jacquinc.admin.sys.service;

import com.google.common.collect.Maps;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.entity.RoleEntity;
import com.jacquinc.admin.sys.entity.RoleEntityCondition;
import com.jacquinc.admin.sys.vo.RoleVO;
import com.jacquinc.admin.sys.vo.RoleVOExt;
import com.jacquinc.admin.utils.DateUtils;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cjq
 * created on  2021/03/11
 */
@Service
@Transactional
public class RoleService extends BaseServiceImpl implements IRoleService {

    private static final String MAPPER_NAMESPACE = "com.jacquinc.admin.sys.sqlmapper.RoleMapper";

    @Autowired
    private IRolePermissionService rolePermissionService;

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public Page<RoleVOExt, RoleVOExt> getRolePage(Page<RoleVOExt, RoleVOExt> page) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("page", page);
        params.put("condition", page.getCondition() == null ? new RoleVOExt() : page.getCondition());
        List<RoleVOExt> list = this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findRoleList", params);
        int count = this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + ".countRole", params);
        page.setTotalRecord(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<RoleVOExt> findList(RoleVOExt condition) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("condition", condition);
        return this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findRoleList", params);
    }

    @Override
    public List<RoleVOExt> findSelectList(String inputValue, List<String> roleTypes) {
        Map<String, Object> params = Maps.newHashMap();
        // 获取权限最大的角色类型（值越小权限越大）
        String roleType = roleTypes.stream().min(String.CASE_INSENSITIVE_ORDER).orElse(null);
        params.put("inputValue", inputValue);
        params.put("roleType", roleType);
        List<RoleVOExt> list = this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findRoleSelectList", params);
        // 非【系统管理员】没有【参数配置员】权限
        /*if (!roleTypes.contains(RoleTypeEnum._01.getCode())) {
            list.removeIf(type -> type.getRoleType().equals(RoleTypeEnum._11.getCode()));
        }*/
        return list;
    }

    @Override
    public ResponseResult save(RoleVOExt roleVO, String userId) {
        RoleEntity saveEntity = null;
        if (countByName(roleVO.getId(), roleVO.getCorpId(), roleVO.getName()) > 0) {
            return new ResponseResult(false, "name", roleVO.getName() + " 已经存在");
        }
        //新增
        if (StringUtils.isEmpty(roleVO.getId())) {
            //保存
            RoleEntity entity = BeanUtils.copyToNewBean(roleVO, RoleEntity.class);
            entity.setId(null);
            entity.setCreateTs(DateUtils.getCurrentTime());
            entity.setCreateUserId(userId);
            saveEntity = this.saveWithQuery(entity);
            //修改
        } else if (roleVO.getId() != null) {
            //保存
            RoleEntity entity = BeanUtils.copyToNewBean(roleVO, RoleEntity.class);
            entity.setUpdateTs(DateUtils.getCurrentTime());
            entity.setUpdateUserId(userId);
            saveEntity = this.saveWithQuery(entity);
            //删除 角色关联权限
            if (StringUtils.isNotEmpty(saveEntity.getId())) {
                rolePermissionService.deleteByRoleId(saveEntity.getId());
            }
        }
        if (saveEntity != null) {
            //批量新增
            rolePermissionService.batchAdd(roleVO.getPermissionIds(), saveEntity.getId());
            return new ResponseResult(true, null, "保存成功", saveEntity.getId());
        } else {
            return new ResponseResult(false, null, "保存失败");
        }
    }

    @Override
    public ResponseResult delete(String id) {
        if (userRoleService.countByRoleId(id) > 0) {
            return new ResponseResult(false, null, "删除失败，还有用户正在使用，不允许删除");
        }
        //删除 角色关联权限
        rolePermissionService.deleteByRoleId(id);
        //删除角色
        if (this.deleteByPrimaryKey(RoleEntity.class, id) > 0) {
            return new ResponseResult("删除成功");
        } else {
            return new ResponseResult(false, null, "删除失败");
        }
    }

    @Override
    public List<RoleVOExt> findByCorpId(String corpId) {
        RoleEntityCondition condition = new RoleEntityCondition();
        condition.createCriteria().andCorpIdEqualTo(corpId);
        List<RoleEntity> roleEntities = this.selectByCondition(condition);
        return BeanUtils.copyToNewList(roleEntities, RoleVOExt.class);
    }

    @Override
    public RoleVO getRoleById(String roleId) {
        RoleEntity entity = this.selectByPrimaryKey(RoleEntity.class, roleId);
        return BeanUtils.copyToNewBean(entity, RoleVO.class);
    }

    @Override
    public List<RoleVOExt> findListByUserId(String userId) {
        RoleVOExt condition = new RoleVOExt();
        condition.setUserId(userId);
        return findList(condition);
    }

    @Override
    public RoleVOExt getRoleByType(String roleType) {
        RoleEntityCondition condition = new RoleEntityCondition();
        RoleEntityCondition.Criteria criteria = condition.createCriteria();
        criteria.andRoleTypeEqualTo(roleType);
        return BeanUtils.copyToNewBean(this.getDao().selectOneByCondition(condition), RoleVOExt.class);
    }

    private long countByName(String id, String corpId, String name) {
        RoleEntityCondition condition = new RoleEntityCondition();
        RoleEntityCondition.Criteria criteria = condition.createCriteria();
        criteria.andNameEqualTo(name);
        if (StringUtils.isNotEmpty(id)) {
            criteria.andIdNotEqualTo(id);
        }
        if (StringUtils.isNotEmpty(corpId)) {
            criteria.andCorpIdEqualTo(corpId);
        }
        return this.countByCondition(condition);
    }
}