package com.jacquinc.admin.sys.service;

import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.vo.UserVO;
import com.jacquinc.admin.sys.vo.UserVOExt;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.exception.BusinessException;

import java.util.List;

/**
 * Created by zhengzheng on 2021/1/25.
 */
public interface IUserService {

    String getDefaultPassword();

    /**
     * 获取登录用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserVOExt getLoginUser(String username);

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return 实体
     */
    UserVOExt getUserInfoById(String id);

    /**
     * 用户页面
     *
     * @param page 页面对象
     * @return 页面对象
     */
    Page<UserVOExt, UserVOExt> getPage(Page<UserVOExt, UserVOExt> page);

    /**
     * 用户保存
     *
     * @param userVO 用户对象
     * @param userId 当前用户ID
     * @return 响应结果
     */
    ResponseResult save(UserVOExt userVO, String userId) throws BusinessException;

    /**
     * 用户保存
     *
     * @param userList 用户对象列表
     * @param userId 当前用户ID
     * @return 响应结果
     */
    ResponseResult save(List<UserVOExt> userList, String userId);

    /**
     * 校验
     *
     * @param userVO 用户对象
     * @throws BusinessException 业务异常
     */
    void validate(UserVOExt userVO) throws BusinessException;

    /**
     * 重置密码
     *
     * @param userId 用户ID
     * @return 响应结果
     */
    ResponseResult resetPassword(String userId);

    /**
     * 根据id删除用户
     *
     * @param userId
     * @return
     */
    ResponseResult deleteUserById(String userId, String currentUserId) throws BusinessException;

    List<UserVOExt> getUserByRoleLevelAndRole(String roleLevel, String role) throws BusinessException;

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return 实体
     */
    UserVO getUserById(String id) throws BusinessException;


    /**
     * 修改密码
     *
     * @param userId             用户ID
     * @param oldPassword        旧密码
     * @param newPassword        新密码
     * @param confirmNewPassword 确认新密码
     * @return 响应结果
     */
    ResponseResult updatePassword(String userId, String oldPassword, String newPassword, String confirmNewPassword) throws BusinessException;

    /**
     * 修改默认密码
     *
     * @param userId
     * @param newPassword
     * @param confirmNewPassword
     * @return
     */
    ResponseResult updateDefaultPassword(String userId, String newPassword, String confirmNewPassword);

    /**
     * 获取用户信息
     *
     * @param realName 用户名称
     * @param corpId 企业id
     * @return 实体
     */
    List<UserVO> getUserByRealName(String realName, String corpId) throws BusinessException;

    /**
     * 获取用户列表
     * @param corpId 企业id
     * @return 实体
     */
    List<UserVOExt> getListByCorpId(String corpId) throws BusinessException;

    void updateLoginData(String id, String ipAddress);

    UserVOExt loginCheck(String username, String password, String deviceuuid);
}
