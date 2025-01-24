package com.jacquinc.admin.sys.service;

import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.exception.BusinessException;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.vo.AnnounceVOExt;

import java.util.List;

public interface IAnnounceService {

    Page<AnnounceVOExt, AnnounceVOExt> getPage(Page<AnnounceVOExt, AnnounceVOExt> page) throws BusinessException;

    ResponseResult save(AnnounceVOExt vo, String userId) throws BusinessException;

    AnnounceVOExt getById(String id) throws BusinessException;

    ResponseResult delete(String id) throws BusinessException;

    List<AnnounceVOExt> getPublicAnnounce() throws BusinessException;
}
