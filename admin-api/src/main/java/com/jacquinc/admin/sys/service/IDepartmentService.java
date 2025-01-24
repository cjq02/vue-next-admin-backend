package com.jacquinc.admin.sys.service;

import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.exception.BusinessException;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.vo.DepartmentVO;
import com.jacquinc.admin.sys.vo.DepartmentVOExt;

import java.util.List;

/**
 * Created by zhengzheng on 2020/2/19.
 */
public interface IDepartmentService {

    /**
     * 下拉列表
     *
     * @param corpId 企业ID
     * @return 下拉列表
     */
    List<DepartmentVO> findSelectList(String corpId, String inputValue);

    List<DepartmentVOExt> findByProjectId(String projectId);

    Page<DepartmentVO, DepartmentVO> getDepartmentPage(Page<DepartmentVO, DepartmentVO> page);

    /**
     * 根据编码查询部门
     *
     * @param code 编码
     * @return 部门
     */
    DepartmentVOExt getDepartmentByCode(String code);

    /**
     * 保存部门
     *
     * @param vo     实体
     * @param userId 用户ID
     * @return 实体
     * @throws BusinessException
     */
    ResponseResult saveDepartment(DepartmentVOExt vo, String userId) throws BusinessException;

    /**
     * 批量保存
     *
     * @param list 列表
     * @param currentUserId 当前用户ID
     * @throws BusinessException 业务异常
     */
    ResponseResult saveDepartmentList(List<DepartmentVOExt> list, String currentUserId) throws BusinessException;

    /**
     * 通过id获取
     * @param deptId
     * @return
     * @throws BusinessException
     */
    DepartmentVO getById(String deptId) throws BusinessException;

    ResponseResult deleteDepartmentById(String id) throws BusinessException;


    /**
     * 下拉部门合集列表
     *
     * @param corpId 企业ID
     * @return 下拉列表
     */
    List<DepartmentVO> getDeptList(String corpId, String inputValue);

}
