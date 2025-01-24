package com.jacquinc.admin.sys.service;

import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import com.jacquinc.admin.sys.entity.LogEntity;
import com.jacquinc.admin.sys.vo.LogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhengzheng on 2019/10/30.
 */
@Service
@Transactional
public class LogService extends BaseServiceImpl implements ILogService {

    @Override
    public void save(LogVO logVO) {
        LogEntity entity = BeanUtils.copyToNewBean(logVO, LogEntity.class);
        this.insert(entity);
    }
}
