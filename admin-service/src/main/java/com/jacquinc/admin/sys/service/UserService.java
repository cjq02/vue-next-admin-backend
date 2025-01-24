package com.jacquinc.admin.sys.service;

import com.google.common.collect.Maps;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.enumerate.YesNoEnum;
import com.jacquinc.admin.application.utils.PasswordUtil;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.entity.UserEntity;
import com.jacquinc.admin.sys.entity.UserEntityCondition;
import com.jacquinc.admin.sys.vo.RoleVOExt;
import com.jacquinc.admin.sys.vo.UserVO;
import com.jacquinc.admin.sys.vo.UserVOExt;
import com.jacquinc.admin.utils.DateUtils;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.base.utils.JsonCloneUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.cache.cache.ICacheClient;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cjq
 * created on  2021/03/11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends BaseServiceImpl implements IUserService {

    private static final String MAPPER_NAMESPACE = "com.jacquinc.admin.sys.sqlmapper.UserMapper";

    @Autowired
    private ICacheClient cacheClient;

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @Value("${app.defaultPassword}")
    private String defaultPassword;

    @Override
    public String getDefaultPassword() {
        return defaultPassword;
    }

    @Override
    public UserVOExt getLoginUser(String username) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("username", username);
        UserVOExt userVOExt = this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + ".getLoginUser", params);
        List<RoleVOExt> roleList = roleService.findListByUserId(userVOExt.getId());
        userVOExt.setRoleIds(roleList.stream().map(RoleVOExt::getId).collect(Collectors.toList()));
        userVOExt.setRoleNames(roleList.stream().map(RoleVOExt::getName).collect(Collectors.toList()));
        userVOExt.setRoleTypes(roleList.stream().map(RoleVOExt::getRoleType).collect(Collectors.toList()));
        List<String> permissionCodes = permissionService.findPermissionCodeListByRoleIds(userVOExt.getRoleIds());
        userVOExt.setPermissions(permissionCodes);
        return userVOExt;
    }

    @Override
    public UserVOExt getUserInfoById(String id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        UserVOExt user = this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + ".getUserById", params);
        List<RoleVOExt> roleList = roleService.findListByUserId(user.getId());
        user.setRoleIds(roleList.stream().map(RoleVOExt::getId).collect(Collectors.toList()));
        user.setRoleNames(roleList.stream().map(RoleVOExt::getName).collect(Collectors.toList()));
        user.setRoleTypes(roleList.stream().map(RoleVOExt::getRoleType).collect(Collectors.toList()));
        List<String> permissionCodes = permissionService.findPermissionCodeListByRoleIds(user.getRoleIds());
        user.setPermissions(permissionCodes);
        return user;
    }

    @Override
    public Page<UserVOExt, UserVOExt> getPage(Page<UserVOExt, UserVOExt> page) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("condition", page.getCondition());
        params.put("page", page);

        long totalRecord = this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + ".countUser", params);
        List<UserVOExt> records = this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findUserList", params);

        page.setTotalRecord(totalRecord);
        page.setRecords(records);
        return page;
    }

    @Override
    public ResponseResult save(UserVOExt userVO, String userId) {
        UserEntity userEntity;
        if (StringUtils.isEmpty(userVO.getJobNo())) {
            userVO.setJobNo(null);
        }
        if (StringUtils.isEmpty(userVO.getId())) {
            userVO.setId(null);
        }
        validate(userVO);
        userEntity = doSave(userVO, userId);
        return new ResponseResult(null, "保存成功", userEntity.getId());
    }

    @Override
    public void validate(UserVOExt userVO) {
        if (StringUtils.isNotEmpty(userVO.getUserName()) && this.countByUserName(userVO.getId(), userVO.getUserName()) > 0) {
            throw new BusinessException("保存失败，用户名：" + userVO.getUserName() + " 已经存在");
        }
        if (StringUtils.isNotEmpty(userVO.getJobNo()) && this.countByJobNo(userVO.getId(), userVO.getJobNo()) > 0) {
            throw new BusinessException("保存失败，工号：" + userVO.getJobNo() + " 已经存在");
        }
        if (StringUtils.isNotEmpty(userVO.getPhone()) && this.countByPhone(userVO.getId(), userVO.getPhone()) > 0) {
            throw new BusinessException("保存失败，手机号：" + userVO.getPhone() + " 已经存在");
        }
    }

    private UserEntity doSave(UserVOExt userVO, String userId) {
        UserEntity userEntity;
        if (StringUtils.isEmpty(userVO.getId())) {
            //加密密码，加盐
            PasswordUtil.HashPassword hashPassword = PasswordUtil.encrypt(defaultPassword);
            userVO.setSalt(hashPassword.getSalt());
            userVO.setPassword(hashPassword.getPassword());
            //保存
            UserEntity entity = BeanUtils.copyToNewBean(userVO, UserEntity.class);
            // userVO.id是空字符串
            entity.setId(null);
            entity.setCreateUserId(userId);
            entity.setCreateTs(new Date());
            entity.setIsDefaultPwd(YesNoEnum.YES.getCode());
            entity.setDelFlag(YesNoEnum.NO.getCode());
            userEntity = this.saveWithQuery(entity);
        } else {
            //保存
            UserEntity entity = BeanUtils.copyToNewBean(userVO, UserEntity.class);
            entity.setUpdateUserId(userId);
            entity.setUpdateTs(new Date());
            userEntity = this.saveWithQuery(entity);
        }
        //先删除
        if (StringUtils.isNotEmpty(userVO.getId())) {
            userRoleService.deleteByUserId(userEntity.getId());
        }
        userRoleService.batchAdd(userVO.getRoleIds(), userEntity.getId());
        return userEntity;
    }

    @Override
    public ResponseResult save(List<UserVOExt> userList, String userId) {
        userList.forEach(user -> save(user, userId));
        return new ResponseResult("保存成功");
    }

    private long countByUserName(String id, String userName) {
        UserEntityCondition condition = new UserEntityCondition();
        if (StringUtils.isNotBlank(id)) {
            condition.createCriteria().andIdNotEqualTo(id).andUserNameEqualTo(userName).andDelFlagNotEqualTo(Constants.YES);
        } else {
            condition.createCriteria().andUserNameEqualTo(userName).andDelFlagNotEqualTo(Constants.YES);;
        }
        return this.countByCondition(condition);
    }

    private long countByJobNo(String id, String jobNo) {
        UserEntityCondition condition = new UserEntityCondition();
        if (StringUtils.isNotBlank(id)) {
            condition.createCriteria().andIdNotEqualTo(id).andJobNoEqualTo(jobNo).andDelFlagNotEqualTo(Constants.YES);
        } else {
            condition.createCriteria().andJobNoEqualTo(jobNo).andDelFlagNotEqualTo(Constants.YES);
        }
        return this.countByCondition(condition);
    }

    private long countByPhone(String id, String phone) {
        UserEntityCondition condition = new UserEntityCondition();
        if (StringUtils.isNotBlank(id)) {
            condition.createCriteria().andIdNotEqualTo(id).andPhoneEqualTo(phone).andDelFlagNotEqualTo(Constants.YES);
        } else {
            condition.createCriteria().andPhoneEqualTo(phone).andDelFlagNotEqualTo(Constants.YES);
        }
        return this.countByCondition(condition);
    }

    @Override
    public ResponseResult resetPassword(String userId) {
        UserEntityCondition condition = new UserEntityCondition();
        condition.createCriteria().andIdEqualTo(userId);
        UserEntity entity = this.selectOneByCondition(condition);
        if (entity == null) {
            return new ResponseResult(false, null, "密码修改失败，该用户不存在！");
        }
        //加密密码，加盐
        PasswordUtil.HashPassword hashPassword = PasswordUtil.encrypt(defaultPassword);
        entity.setSalt(hashPassword.getSalt());
        entity.setPassword(hashPassword.getPassword());
        entity.setIsDefaultPwd(YesNoEnum.YES.getCode());
        //
        if (this.update(entity) == 0) {
            return new ResponseResult(false, null, "密码修改失败！");
        }

        return new ResponseResult("密码修改成功");
    }

    @Override
    public ResponseResult deleteUserById(String userId, String currentUserId) throws BusinessException {
        if (Constants.ADMIN_USER_ID.equals(userId)) {
            throw new BusinessException("超级管理员不能删除");
        }
        userRoleService.deleteByUserId(userId);
        UserEntity entity = new UserEntity();
        entity.setId(userId);
        entity.setDelFlag(Constants.YES);
        entity.setDelTs(DateUtils.getCurrentTime());
        entity.setDelUserId(currentUserId);
        entity.setUpdateUserId(currentUserId);
        entity.setUpdateTs(new Date());
        this.update(entity);
        return new ResponseResult("删除成功");
    }

    @Override
    public List<UserVOExt> getUserByRoleLevelAndRole(String roleLevel, String role) throws BusinessException {
        logger.info("getUserByRoleLevelAndRole roleLevel={}, role={}", roleLevel, role);
        Map<String, Object> params = new HashMap<>();
        params.put("roleLevel", roleLevel);
        params.put("role", role);
        return this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".getUserByRoleLevelAndRole", params);
    }

    @Override
    public UserVO getUserById(String id) throws BusinessException {
        logger.info("getUserById id={}", id);
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException("id不能为空！");
        }
        return JsonCloneUtils.cloneFrom(selectByPrimaryKey(UserEntity.class, id), UserVO.class);
    }

    @Override
    public ResponseResult updatePassword(String userId, String oldPassword, String newPassword, String confirmNewPassword) {
        UserEntityCondition condition = new UserEntityCondition();
        condition.createCriteria().andIdEqualTo(userId);
        UserEntity entity = this.selectOneByCondition(condition);
        if (entity == null) {
            throw new BusinessException("密码修改失败，该用户不存在");
        }
        if (!PasswordUtil.encrypt(oldPassword, entity.getSalt()).getPassword().equals(entity.getPassword())) {
            throw new BusinessException("密码修改失败，旧密码错误！");
        }
        if (!newPassword.equals(confirmNewPassword)) {
            throw new BusinessException("两次新密码输入不一致");
        }
        //
        //加密密码，加盐
        PasswordUtil.HashPassword hashNewPassword = PasswordUtil.encrypt(newPassword);
        entity.setSalt(hashNewPassword.getSalt());
        entity.setPassword(hashNewPassword.getPassword());
        entity.setIsDefaultPwd(YesNoEnum.NO.getCode());
        if (this.update(entity) == 0) {
            throw new BusinessException("密码修改失败");
        }
        return new ResponseResult("密码修改成功");
    }

    @Override
    public ResponseResult updateDefaultPassword(String userId, String newPassword, String confirmNewPassword) {
        UserEntityCondition condition = new UserEntityCondition();
        condition.createCriteria().andIdEqualTo(userId);
        UserEntity entity = this.selectOneByCondition(condition);
        if (!newPassword.equals(confirmNewPassword)) {
            throw new BusinessException("两次新密码输入不一致");
        }
        //
        //加密密码，加盐
        PasswordUtil.HashPassword hashNewPassword = PasswordUtil.encrypt(newPassword);
        entity.setSalt(hashNewPassword.getSalt());
        entity.setPassword(hashNewPassword.getPassword());
        entity.setIsDefaultPwd(YesNoEnum.NO.getCode());
        if (this.update(entity) == 0) {
            throw new BusinessException("密码修改失败");
        }
        return new ResponseResult("密码修改成功");
    }

    @Override
    public List<UserVO> getUserByRealName(String realName, String corpId) throws BusinessException {
        logger.info("getUserByRealName realName={}", realName);
        if (StringUtils.isEmpty(realName)) {
            throw new BusinessException("realName不能为空！");
        }
        UserEntityCondition condition = new UserEntityCondition();
        condition.createCriteria().andCorpIdEqualTo(corpId).andRealNameEqualTo(realName);
        return JsonCloneUtils.cloneForList(selectByCondition(condition), UserVO.class);
    }

    @Override
    public List<UserVOExt> getListByCorpId(String corpId) throws BusinessException {
        logger.info("getListByCorpId corpId={}", corpId);
        if (StringUtils.isEmpty(corpId)) {
            throw new BusinessException("corpId不能为空！");
        }
        UserEntityCondition condition = new UserEntityCondition();
        condition.createCriteria().andCorpIdEqualTo(corpId);
        return JsonCloneUtils.cloneForList(selectByCondition(condition), UserVOExt.class);
    }

    @Override
    public void updateLoginData(String id, String ipAddress) {
        UserEntity entity = this.selectByPrimaryKey(UserEntity.class, id);
        entity.setLastLoginIp(ipAddress);
        entity.setLastLoginTs(DateUtils.getCurrentDate());
        entity.setUpdateUserId(id);
        entity.setUpdateTs(DateUtils.getCurrentDate());
        this.update(entity);
    }

    @Override
    public UserVOExt loginCheck(String username, String password, String deviceuuid) {
        UserVOExt userVOExt = getLoginUser(username);
        if (userVOExt == null) {
            loginFail(deviceuuid);
            throw new BusinessException("账号错误，请重新输入！");
        }
        if (Constants.NO.equals(userVOExt.getActive())) {
            throw new BusinessException("用户被锁定，请联系客服！");
        }
        if (StringUtils.isNotEmpty(password) &&
                !PasswordUtil.encrypt(password, userVOExt.getSalt()).getPassword().equals(userVOExt.getPassword())) {
            loginFail(deviceuuid);
            throw new BusinessException("账号或者验证码错误，请重新输入！");
        }
        cacheClient.remove(Constants.CACHE_GROUP, Constants.CacheKey.LOGIN_FAIL_PREFIX_KEY + deviceuuid);
        return userVOExt;
    }

    private void loginFail(String deviceuuid) {
        String failKey = Constants.CacheKey.LOGIN_FAIL_PREFIX_KEY + deviceuuid;
        if (cacheClient.isExist(Constants.CACHE_GROUP, failKey)) {
            cacheClient.put(Constants.CACHE_GROUP, failKey, (int) cacheClient.get(Constants.CACHE_GROUP, failKey) + 1);
        } else {
            cacheClient.put(Constants.CACHE_GROUP, failKey, 1);
        }
    }
}
