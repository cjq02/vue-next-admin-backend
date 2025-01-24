package com.jacquinc.admin.sys.service;

import com.alibaba.fastjson.JSON;
import com.jacquinc.admin.sys.enumerate.AnnounceStatusEnum;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.JsonCloneUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.entity.AnnounceEntity;
import com.jacquinc.admin.sys.entity.AnnounceEntityCondition;
import com.jacquinc.admin.sys.vo.AnnounceVOExt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AnnounceService extends BaseServiceImpl implements IAnnounceService {

    private static final String MAPPER_NAMESPACE = "com.jacquinc.admin.sys.sqlmapper.AnnounceMapper";

    @Override
    public Page<AnnounceVOExt, AnnounceVOExt> getPage(Page<AnnounceVOExt, AnnounceVOExt> page) throws BusinessException {
        logger.info("getPage page={}", JSON.toJSONString(page));
        AnnounceVOExt condition = page.getCondition();
        Map<String, Object> params = new HashMap<>();
        params.put("condition", condition);
        params.put("page", page);
        int count  = getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + ".countAnnouncePage", params);
        List<AnnounceVOExt> list = getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".getAnnouncePage", params);
        page.setTotalRecord(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public ResponseResult save(AnnounceVOExt vo, String userId) throws BusinessException {
        logger.info("save vo={}", JSON.toJSONString(vo));
        AnnounceEntity announce = JsonCloneUtils.cloneFrom(vo, AnnounceEntity.class);
        if (StringUtils.isEmpty(announce.getId())) {
            announce.setId(null);
            announce.setCreateUserId(userId);
            announce.setCreateTs(new Date());
            announce.setUpdateUserId(userId);
            announce.setUpdateTs(new Date());
            announce = this.insertWithQuery(announce);
        } else {
            announce.setUpdateUserId(userId);
            announce.setUpdateTs(new Date());
            this.update(announce);
        }
        return new ResponseResult(true, "", "保存成功！", announce);
    }

    @Override
    public AnnounceVOExt getById(String id) throws BusinessException {
        logger.info("getById id={}", id);
        return JsonCloneUtils.cloneFrom(selectByPrimaryKey(AnnounceEntity.class, id), AnnounceVOExt.class);
    }

    @Override
    public ResponseResult delete(String id) throws BusinessException {
        logger.info("deleteBudgetOrg id={}", id);
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException("入参错误!");
        }
        this.deleteByPrimaryKey(AnnounceEntity.class, id);
        return new ResponseResult(null, "删除成功！", null);
    }

    @Override
    public List<AnnounceVOExt> getPublicAnnounce() throws BusinessException {
        AnnounceEntityCondition condition = new AnnounceEntityCondition();
        condition.createCriteria().andStatusEqualTo(AnnounceStatusEnum._1.getCode());
        condition.setOrderByClause("update_ts desc");
        return JsonCloneUtils.cloneForList(selectByCondition(condition), AnnounceVOExt.class);
    }
}
