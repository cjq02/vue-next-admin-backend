package com.jacquinc.admin.sys.service;

import com.jacquinc.admin.sys.vo.FileVO;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jacquinc.admin.sys.vo.FileVOExt;

import java.util.List;

public interface IFileService {

    ResponseResult deleteById(String id);

    void deleteByBiz(String bizId, String bizType);

    FileVO save(FileVO fileVO, String userId);

    FileVO getById(String id);

    void deleteByBizs(List<String> bizIds, String bizType);

    void deleteByIds(List<String> oldFileIdList);

    List<FileVO> findListByBizId(String bizId);

    List<FileVO> findListByBizIdAndType(String bizId, String bizType);

    List<FileVO> findList(FileVOExt condition);
}
