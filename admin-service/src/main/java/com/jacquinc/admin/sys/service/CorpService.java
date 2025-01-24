package com.jacquinc.admin.sys.service;

import com.google.common.collect.Maps;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.entity.*;
import com.jacquinc.admin.sys.enumerate.CorpTypeEnum;
import com.jacquinc.admin.sys.vo.CorpVOExt;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.base.utils.DateUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CorpService extends BaseServiceImpl implements ICorpService {

    private static final String MAPPER_NAMESPACE = "com.jacquinc.admin.sys.sqlmapper.CorpMapper";

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;


    @Override
    public ResponseResult addAndModify(CorpVOExt vo, String userId) {
        if (countCorpByName(vo.getId(), vo.getName()) > 0) {
            throw new BusinessException("保存失败，名称：" + vo.getName() + " 已经存在");
        }
        if (countCorpByCode(vo.getId(), vo.getCode()) > 0) {
            throw new BusinessException("保存失败，编号：" + vo.getCode() + " 已经存在");
        }
        CorpEntity entity = BeanUtils.copyToNewBean(vo, CorpEntity.class);
        if (StringUtils.isNotEmpty(vo.getParentCorpId()) && vo.getParentCorpId().equals(vo.getId())) {
            throw new BusinessException("上级单位不能是自己！");
        }
        if (StringUtils.isEmpty(vo.getParentCorpId())) {
            entity.setCorpType(CorpTypeEnum.TYPE_1.getCode());
        } else {
            //设置单位级别
            CorpEntityCondition condition = new CorpEntityCondition();
            condition.createCriteria().andIdEqualTo(vo.getParentCorpId());
            CorpEntity parentEntity = this.selectOneByCondition(condition);
            if (CorpTypeEnum.TYPE_3.getCode().equals(parentEntity.getCorpType())) {
                throw new BusinessException("三级单位不允许添加子单位");
            }
            if (CorpTypeEnum.TYPE_1.getCode().equals(parentEntity.getCorpType())) {
                entity.setCorpType(CorpTypeEnum.TYPE_2.getCode());
            }
            if (CorpTypeEnum.TYPE_2.getCode().equals(parentEntity.getCorpType())) {
                entity.setCorpType(CorpTypeEnum.TYPE_3.getCode());
            }
        }
        //新增单位
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setId(null);
            entity.setCreateUserId(userId);
            entity.setCreateTs(new Date());
            entity = this.saveWithQuery(entity);
        } else {
            entity.setUpdateUserId(userId);
            entity.setUpdateTs(DateUtils.getCurrentDate());
            this.update(entity);
        }
        return new ResponseResult(null, "保存成功！", entity.getId());
    }

    @Override
    public ResponseResult deleteCorpById(String corpId) {
        DepartmentEntityCondition condition = new DepartmentEntityCondition();
        condition.createCriteria().andCorpIdEqualTo(corpId);
        long count = this.countByCondition(condition);
        if (count > 0) {
            throw new BusinessException("该单位底下存在部门，无法删除");
        }
        this.deleteByPrimaryKey(CorpEntity.class, corpId);
        RoleEntityCondition roleCondition = new RoleEntityCondition();
        roleCondition.createCriteria().andCorpIdEqualTo(corpId);
        List<RoleEntity> roleEntities = this.selectByCondition(roleCondition);
        List<String> roleIds = new ArrayList<>();
        roleEntities.forEach(roleEntity -> roleIds.add(roleEntity.getId()));
        if (!roleIds.isEmpty()) {
            RolePermissionEntityCondition rolePermissionEntityCondition = new RolePermissionEntityCondition();
            rolePermissionEntityCondition.createCriteria().andRoleIdIn(roleIds);
            this.deleteByCondition(rolePermissionEntityCondition);
        }
        this.deleteByCondition(roleCondition);
        return new ResponseResult("删除成功！");
    }

    @Override
    public Page<CorpVOExt, CorpVOExt> getPage(Page<CorpVOExt, CorpVOExt> page) {
        List<CorpVOExt> records = findList(page.getCondition());
        page.setTotalRecord(records.size());
        page.setRecords(records);
        return page;
    }

    @Override
    public List<CorpVOExt> findList(CorpVOExt condition) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("condition", condition);
        return this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findCorpList", params);
    }

    @Override
    public CorpVOExt getCorpInfoById(String corpId) throws BusinessException {
        logger.info("getCorpInfoById corpId={}", corpId);
        if (StringUtils.isEmpty(corpId)) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("corpId", corpId);
        return this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + ".getCorpInfoById", params);
    }

    private List<CorpVOExt> queryByParentId(List<CorpVOExt> list, String parentId) {
        List<CorpVOExt> newList = new ArrayList<>();
        for (CorpVOExt vo : list) {
            if (parentId.equals(vo.getParentCorpId())) {
                newList.add(vo);
            }
        }
        return newList;
    }


    public long countCorpByName(String id, String name) {
        CorpEntityCondition condition = new CorpEntityCondition();
        if (StringUtils.isNotBlank(id)) {
            condition.createCriteria().andIdNotEqualTo(id).andNameEqualTo(name);
        } else {
            condition.createCriteria().andNameEqualTo(name);
        }
        return this.countByCondition(condition);
    }

    private long countCorpByCode(String id, String code) {
        CorpEntityCondition condition = new CorpEntityCondition();
        if (StringUtils.isNotBlank(id)) {
            condition.createCriteria().andIdNotEqualTo(id).andCodeEqualTo(code);
        } else {
            condition.createCriteria().andCodeEqualTo(code);
        }
        return this.countByCondition(condition);
    }


}
