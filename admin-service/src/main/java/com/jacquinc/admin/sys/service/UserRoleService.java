package com.jacquinc.admin.sys.service;

import com.jacquinc.admin.sys.entity.UserRoleEntity;
import com.jacquinc.admin.sys.entity.UserRoleEntityCondition;
import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.base.utils.UUIDUtils;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import com.jacquinc.admin.sys.vo.UserRoleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiezm
 */
@Service
@Transactional
public class UserRoleService extends BaseServiceImpl implements IUserRoleService {

    @Override
    public List<UserRoleVO> findListByUserId(String userId) {
        // 先查询用户的所有 角色ID集合
        UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();
        userRoleEntityCondition.createCriteria().andUserIdEqualTo(userId);
        return BeanUtils.copyToNewList(selectByCondition(userRoleEntityCondition), UserRoleVO.class);
    }

    @Override
    public long countByRoleId(String roleId) {
        //先查询用户的所有 角色ID集合
        UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();
        userRoleEntityCondition.createCriteria().andRoleIdEqualTo(roleId);
        return countByCondition(userRoleEntityCondition);
    }

    @Override
    public int deleteByUserId(String userId) {
        UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();
        userRoleEntityCondition.createCriteria().andUserIdEqualTo(userId);
        return this.deleteByCondition(userRoleEntityCondition);
    }

    @Override
    public void add(UserRoleVO userRole) {
        this.insert(toEntity(userRole));
    }

    private UserRoleEntity toEntity(UserRoleVO userRole) {
        return BeanUtils.copyToNewBean(userRole, UserRoleEntity.class);
    }

    @Override
    public int batchAdd(List<String> roleIds, String userId) {
        List<UserRoleEntity> userRoleEntities = new ArrayList<>();
        for (String roleId : roleIds) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setId(UUIDUtils.getStringValue());
            userRoleEntity.setUserId(userId);
            userRoleEntity.setRoleId(roleId);
            userRoleEntities.add(userRoleEntity);
        }
        return this.insertList(userRoleEntities);
    }
}
