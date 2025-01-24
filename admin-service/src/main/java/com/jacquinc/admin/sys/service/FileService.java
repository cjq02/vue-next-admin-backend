package com.jacquinc.admin.sys.service;

import com.jacquinc.admin.sys.entity.FileEntity;
import com.jacquinc.admin.sys.entity.FileEntityCondition;
import com.jacquinc.admin.sys.vo.FileVO;
import com.jacquinc.admin.sys.vo.FileVOExt;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.base.utils.DateUtils;
import com.jiujie.framework.base.utils.JsonCloneUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class FileService extends BaseServiceImpl implements IFileService {

    @Value("${app.openOfficePath}")
    private String openOfficePath;

    @Override
    public ResponseResult deleteById(String id) {
        FileEntity entity = this.selectByPrimaryKey(FileEntity.class, id);
        this.deleteByPrimaryKey(FileEntity.class, id);
        this.deleteFile(entity);
        return new ResponseResult("删除成功！");
    }

    @Override
    public void deleteByBiz(String bizId, String bizType) {
        FileEntityCondition condition = new FileEntityCondition();
        condition.createCriteria().andBizIdEqualTo(bizId).andBizTypeEqualTo(bizType);
        // 删除数据
        this.deleteByCondition(condition);
        // 删除具体文件
        List<FileEntity> entities = this.selectByCondition(condition);
        entities.forEach(this::deleteFile);
    }

    @Override
    public FileVO save(FileVO fileVO, String userId) {
        FileEntity entity = BeanUtils.copyToNewBean(fileVO, FileEntity.class);
        if (StringUtils.isEmpty(fileVO.getId())) {
            entity.setId(null);
            entity.setCreateUserId(userId);
            entity.setCreateTs(DateUtils.getCurrentDate());
        } else {
            entity.setUpdateTs(DateUtils.getCurrentDate());
            entity.setUpdateUserId(userId);
        }
        return BeanUtils.copyToNewBean(this.saveWithQuery(entity), FileVO.class);
    }

    @Override
    public FileVO getById(String id) {
        FileEntity fileEntity = this.selectByPrimaryKey(FileEntity.class, id);
        return BeanUtils.copyToNewBean(fileEntity, FileVO.class);
    }

    @Override
    public void deleteByBizs(List<String> bizIds, String bizType) {
        if (bizIds != null && !bizIds.isEmpty()) {
            FileEntityCondition condition = new FileEntityCondition();
            condition.createCriteria().andBizIdIn(bizIds).andBizTypeEqualTo(bizType);
            // 删除数据
            this.deleteByCondition(condition);
            // 删除具体文件
            List<FileEntity> entities = this.selectByCondition(condition);
            entities.forEach(this::deleteFile);
        }
    }

    @Override
    public void deleteByIds(List<String> oldFileIdList) {
        if (oldFileIdList != null && !oldFileIdList.isEmpty()) {
            FileEntityCondition condition = new FileEntityCondition();
            condition.createCriteria().andIdIn(oldFileIdList);
            // 删除数据
            this.deleteByCondition(condition);
            // 删除具体文件
            List<FileEntity> entities = this.selectByCondition(condition);
            entities.forEach(this::deleteFile);
        }
    }

    private void deleteFile(FileEntity fileEntity) {
        if (StringUtils.isNotEmpty(fileEntity.getFileUrl())) {
            File file = new File(fileEntity.getFileUrl());
            boolean delFlag = file.delete();
            if (!delFlag) {
                logger.error("删除文件失败，文件不在该路经！路经：{}", fileEntity.getFileUrl());
            }
        }
        String suffix = getSuffix(fileEntity.getFileName()).toLowerCase();
        if (".doc".equals(suffix) || ".docx".equals(suffix) || ".xls".equals(suffix) || ".xlsx".equals(suffix)
                || ".ppt".equals(suffix) || ".pptx".equals(suffix) || ".pdf".equals(suffix)) {
            File file = new File(openOfficePath + "/images/" +
                    fileEntity.getFileUrl().split("/")[fileEntity.getFileUrl().split("/").length - 1] + "/");
            if (file.isDirectory()) {
                boolean delFlag = file.delete();
                if (!delFlag) {
                    logger.error("删除文档转图片目录失败，路径不存在！路经：{}", openOfficePath + "/images/" +
                            fileEntity.getFileUrl().split("/")[fileEntity.getFileUrl().split("/").length - 1] + "/");
                }
            }
        }
    }

    private String getSuffix(String fileName) {
        int beginIndex = fileName.lastIndexOf(".");
        if (beginIndex < 0) {
            return "";
        }
        return fileName.substring(beginIndex);
    }

    @Override
    public List<FileVO> findListByBizId(String bizId) {
        FileEntityCondition condition = new FileEntityCondition();
        condition.createCriteria().andBizIdEqualTo(bizId);
        condition.setOrderByClause(" biz_type, create_ts ");
        return JsonCloneUtils.cloneForList(selectByCondition(condition), FileVO.class);
    }

    @Override
    public List<FileVO> findListByBizIdAndType(String bizId, String bizType) {
        FileEntityCondition condition = new FileEntityCondition();
        condition.createCriteria().andBizIdEqualTo(bizId).andBizTypeEqualTo(bizType);
        condition.setOrderByClause("create_ts");
        return JsonCloneUtils.cloneForList(selectByCondition(condition), FileVO.class);
    }

    @Override
    public List<FileVO> findList(FileVOExt condition) {
        FileEntityCondition fileEntityCondition = new FileEntityCondition();
        FileEntityCondition.Criteria criteria = fileEntityCondition.createCriteria();
        if (StringUtils.isNotEmpty(condition.getBizId())) {
            criteria.andBizIdEqualTo(condition.getBizId());
        }
        if (StringUtils.isNotEmpty(condition.getBizType())) {
            criteria.andBizTypeEqualTo(condition.getBizType());
        }
        return JsonCloneUtils.cloneForList(selectByCondition(fileEntityCondition), FileVO.class);
    }
}
