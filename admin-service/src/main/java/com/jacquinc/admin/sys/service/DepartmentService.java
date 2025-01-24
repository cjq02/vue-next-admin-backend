package com.jacquinc.admin.sys.service;

import com.google.common.collect.Lists;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.entity.DepartmentEntity;
import com.jacquinc.admin.sys.entity.DepartmentEntity;
import com.jacquinc.admin.sys.entity.DepartmentEntityCondition;
import com.jacquinc.admin.sys.entity.UserEntityCondition;
import com.jacquinc.admin.sys.vo.DepartmentVOExt;
import com.jacquinc.admin.sys.vo.DepartmentVO;
import com.jacquinc.admin.sys.vo.DepartmentVOExt;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.base.utils.DateUtils;
import com.jiujie.framework.base.utils.JsonCloneUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.base.vo.BaseVO;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhengzheng on 2020/2/19.
 */
@Service
@Transactional
public class DepartmentService extends BaseServiceImpl implements IDepartmentService {

    private static final String MAPPER_NAMESPACE = "com.jacquinc.admin.sys.sqlmapper.DepartmentMapper";

    @Override
    public List<DepartmentVO> findSelectList(String corpId, String inputValue) {
        DepartmentEntityCondition condition = new DepartmentEntityCondition();
        DepartmentEntityCondition.Criteria criteria = condition.createCriteria();
        if (StringUtils.isNotEmpty(corpId)) {
            criteria.andCorpIdEqualTo(corpId);
        }
        if (StringUtils.isNotEmpty(inputValue)) {
            criteria.andNameLike("%" + inputValue + "%");
        }
        List<DepartmentEntity> entities = this.selectByCondition(condition);
        return BeanUtils.copyToNewList(entities, DepartmentVO.class);
    }

    @Override
    public List<DepartmentVOExt> findByProjectId(String projectId) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        return this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findByProjectId", params);
    }

    @Override
    public Page<DepartmentVO, DepartmentVO> getDepartmentPage(Page<DepartmentVO, DepartmentVO> page) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("condition", page.getCondition());

        long totalRecord = this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + ".getDepartmentCount", params);
        List<DepartmentVOExt> records = this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findDepartmentList", params);

        page.setTotalRecord(totalRecord);
        page.setRecords(records);
        return page;
    }

    @Override
    public DepartmentVOExt getDepartmentByCode(String code) {
        DepartmentEntityCondition condition = new DepartmentEntityCondition();
        condition.createCriteria().andCodeEqualTo(code);
        return BeanUtils.copyToNewBean(getDao().selectOneByCondition(condition), DepartmentVOExt.class);
    }

    @Override
    public ResponseResult saveDepartment(DepartmentVOExt vo, String userId) throws BusinessException {
        if (countName(vo.getId(), vo.getCorpId(), vo.getName()) > 0) {
            throw new BusinessException("保存失败，【" + vo.getName() + "】已经存在！");
        }
        if (countCode(vo.getId(), vo.getCode()) > 0) {
            throw new BusinessException("保存失败，部门编码【" + vo.getCode() + "】已经存在！");
        }
        if (StringUtils.isNotEmpty(vo.getId()) && StringUtils.isNotEmpty(vo.getParentId()) &&
                (vo.getId().equals(vo.getParentId()) || countByTreeId(vo.getId()) > 1)) {
            throw new BusinessException("部门上级单位不能选择自己或自己底下的部门！");
        }
        DepartmentEntity entity = BeanUtils.copyToNewBean(vo, DepartmentEntity.class);
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setId(null);
            entity.setCreateUserId(userId);
            entity.setCreateTs(DateUtils.getCurrentDate());
            this.insert(entity);
        } else {
            entity.setUpdateUserId(userId);
            entity.setUpdateTs(DateUtils.getCurrentDate());
            this.update(entity);
        }
        return new ResponseResult(null, "保存成功！", entity.getId());
    }

    @Override
    public ResponseResult saveDepartmentList(List<DepartmentVOExt> list, String currentUserId) {
        List<DepartmentVOExt> addDataList = list.stream().filter(item -> item.getRowState().equals(BaseVO.RowStateEnum.ADDED.getValue())).collect(Collectors.toList());
        List<DepartmentVOExt> updateDataList = list.stream().filter(item -> item.getRowState().equals(BaseVO.RowStateEnum.MODIFIED.getValue())).collect(Collectors.toList());
        addDataList.forEach(vo -> vo.setId(null));
        updateDataList.forEach(vo -> {
            vo.setUpdateUserId(currentUserId);
            vo.setUpdateTs(DateUtils.getCurrentDate());
        });
        // 新增
        this.insertList(BeanUtils.copyToNewList(addDataList, DepartmentEntity.class));
        // 修改
        this.update(BeanUtils.copyToNewList(updateDataList, DepartmentEntity.class));
        return new ResponseResult(null, "保存成功！");
    }

    @Override
    public DepartmentVO getById(String deptId) throws BusinessException {
        if (StringUtils.isEmpty(deptId)) {
            throw new BusinessException("部门id为空！");
        }
        DepartmentEntity entity = selectByPrimaryKey(DepartmentEntity.class, deptId);
        return JsonCloneUtils.cloneFrom(entity, DepartmentVO.class);
    }

    @Override
    public ResponseResult deleteDepartmentById(String id) throws BusinessException {
        UserEntityCondition condition = new UserEntityCondition();
        condition.createCriteria().andDepartmentIdEqualTo(id).andDelFlagNotEqualTo("1");
        long count = this.countByCondition(condition);
        if (count > 0) {
            throw new BusinessException("该部门底下存在人员，不允许删除！");
        }
        this.deleteByPrimaryKey(DepartmentEntity.class, id);
        return new ResponseResult("删除成功！");
    }

    private long countName(String id, String corpId, String name) {
        DepartmentEntityCondition condition = new DepartmentEntityCondition();
        if (StringUtils.isNotEmpty(id)) {
            condition.createCriteria().andIdNotEqualTo(id).andCorpIdEqualTo(corpId).andNameEqualTo(name);
        } else {
            condition.createCriteria().andCorpIdEqualTo(corpId).andNameEqualTo(name);
        }
        return this.countByCondition(condition);
    }

    private long countCode(String id, String code) {
        DepartmentEntityCondition condition = new DepartmentEntityCondition();
        if (StringUtils.isNotEmpty(id)) {
            condition.createCriteria().andIdNotEqualTo(id).andCodeEqualTo(code);
        } else {
            condition.createCriteria().andCodeEqualTo(code);
        }
        return this.countByCondition(condition);
    }

    private long countByTreeId(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + ".countByTreeId", params);
    }

    @Override
    public List<DepartmentVO> getDeptList(String corpId, String inputValue) {
        return getDepartmentList(corpId, "2", inputValue);
    }

    private List<DepartmentVO> getDepartmentList(String corpId, String type, String inputValue) {
        DepartmentEntityCondition condition = new DepartmentEntityCondition();
        DepartmentEntityCondition.Criteria criteria = condition.createCriteria();
        if (StringUtils.isNotEmpty(corpId)) {
            criteria.andCorpIdEqualTo(corpId);
        }
        if (StringUtils.isNotEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }
        if (StringUtils.isNotEmpty(inputValue)) {
            criteria.andNameLike("%" + inputValue + "%");
        }
        condition.setOrderByClause("type, name");
        return JsonCloneUtils.cloneForList(this.selectByCondition(condition), DepartmentVO.class);
    }

}
