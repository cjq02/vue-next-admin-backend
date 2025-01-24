package com.jacquinc.admin.sys.service;

import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.vo.CorpVOExt;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.exception.BusinessException;

import java.util.List;

public interface ICorpService {


    /**
     * 保存单位
     *
     * @param vo
     * @param userId
     * @return
     */
    ResponseResult addAndModify(CorpVOExt vo, String userId) throws BusinessException;

    /**
     * 删除单位
     *
     * @param corpId
     * @return
     */
    ResponseResult deleteCorpById(String corpId) throws BusinessException;

    /**
     * 获取单位页面信息
     *
     * @param page 页面信息
     * @return 页面信息
     */
    Page<CorpVOExt, CorpVOExt> getPage(Page<CorpVOExt, CorpVOExt> page);

    /**
     * 获取单位列表
     *
     * @return 列表
     */
    List<CorpVOExt> findList(CorpVOExt condition);

    /**
     * 获取企业信息
     * @param corpId
     * @return
     * @throws BusinessException
     */
    CorpVOExt getCorpInfoById(String corpId) throws BusinessException;




}
