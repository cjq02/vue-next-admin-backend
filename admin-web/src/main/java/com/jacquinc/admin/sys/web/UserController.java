package com.jacquinc.admin.sys.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.application.web.BaseController;
import com.jacquinc.admin.sys.enumerate.RoleTypeEnum;
import com.jacquinc.admin.sys.service.IUserRoleService;
import com.jacquinc.admin.sys.service.IUserService;
import com.jacquinc.admin.sys.vo.UserRoleVO;
import com.jacquinc.admin.sys.vo.UserVOExt;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.JSONUtils;
import com.jiujie.framework.cache.cache.ICacheClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户管理
 *
 * @author cjq
 * created on  2020/09/11
 */
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
@RestController
@RequestMapping("sys/user")
@Api(tags = "用户管理")
@ApiSort(910)
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private ICacheClient cacheClient;

    /**
     * 获取默认密码
     *
     * @return 响应结果
     */
    @HasPermission("User:edit")
    @RequestMapping(value = "/getDefaultPassword.json", method = RequestMethod.GET)
    @ApiOperation(value = "获取默认密码")
    @ApiOperationSupport(order = 1)
    public String getDefaultPassword() {
        return userService.getDefaultPassword();
    }

    /**
     * 根据ID获取记录
     *
     * @return 响应结果
     */
    @HasPermission("user")
    @RequestMapping(value = "/getUserInfo.json", method = RequestMethod.GET)
    @ApiOperation(value = "获取当前用户")
    @ApiOperationSupport(order = 2)
    public ResponseResult getUserInfo() {
        return new ResponseResult(getCurrentUser());
    }

    @HasPermission("User:view")
    @RequestMapping(method = RequestMethod.POST, value = "/getUserPage.json")
    @ApiOperation("用户分页列表")
    @ApiOperationSupport(order = 3)
    public Page<UserVOExt, UserVOExt> getPage(@RequestBody Page<UserVOExt, UserVOExt> page) {
        if (!getCurrentUser().getRoleTypes().contains(RoleTypeEnum._01.getCode())) {
            page.getCondition().setCorpId(getCurrentCorpId());
            page.getCondition().setExcludeSuperAdmin(Constants.YES);
        }
        return userService.getPage(page);
    }

    @HasPermission("User:view")
    @RequestMapping(value = "/getUserById.json", method = RequestMethod.GET)
    @ApiOperation("获取用户信息")
    @ApiOperationSupport(order = 4)
    public UserVOExt getUserById(String userId) {
        return userService.getUserInfoById(userId);
    }

    @HasPermission("User:edit")
    @RequestMapping(value = "/saveUser.json", method = RequestMethod.POST)
    @ApiOperation("保存用户")
    @ApiOperationSupport(order = 5)
    public ResponseResult saveUser(@RequestBody UserVOExt vo, HttpServletRequest request) {
        ResponseResult result = userService.save(vo, getCurrentUserId());
        UserVOExt sessionUser = (UserVOExt) request.getSession().getAttribute(Constants.SESSION_USER);
        if (sessionUser.getPhone().equals(vo.getPhone())) {
            UserVOExt baseUserVO = userService.getUserInfoById(sessionUser.getId());
            request.getSession().setAttribute(Constants.SESSION_USER, baseUserVO);
            cacheClient.put(Constants.CACHE_GROUP, Constants.CacheKey.USER_INFO_PREFIX_KEY + baseUserVO.getId(), JSONUtils.objectToString(baseUserVO));
        }
        return result;
    }

    @HasPermission("User:view")
    @RequestMapping(value = "/findUserRoleListByUserId.json", method = RequestMethod.GET)
    @ApiOperation("用户角色列表")
    @ApiOperationSupport(order = 6)
    public List<UserRoleVO> findUserRoleListByUserId(String userId) {
        return userRoleService.findListByUserId(userId);
    }

    @HasPermission("User:delete")
    @PostMapping("/deleteUser.json")
    @ApiOperation("删除用户")
    @ApiOperationSupport(order = 7)
    public ResponseResult deleteUserById(String id) {
        return userService.deleteUserById(id, getCurrentUserId());
    }

    @HasPermission("User:edit")
    @GetMapping(value = "/resetPassword.json")
    @ApiOperation("重置密码")
    @ApiOperationSupport(order = 8)
    public ResponseResult resetPassword(String userId) {
        return userService.resetPassword(userId);
    }

    @HasPermission("user")
    @RequestMapping(value = "/updatePassword.json", method = RequestMethod.GET)
    @ApiOperation("修改密码")
    @ApiOperationSupport(order = 9)
    public ResponseResult updatePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        return userService.updatePassword(getCurrentUserId(), oldPassword, newPassword, confirmNewPassword);
    }

    @HasPermission("user")
    @RequestMapping(value = "/updateDefaultPassword.json", method = RequestMethod.GET)
    @ApiOperation(value = "修改密码", notes = "不用旧密码校验")
    @ApiOperationSupport(order = 10)
    public ResponseResult updateDefaultPassword(String newPassword, String confirmNewPassword) {
        ResponseResult res = userService.updateDefaultPassword(getCurrentUserId(), newPassword, confirmNewPassword);
        if (res.isSuccess()) {
            getCurrentUser().setIsDefaultPwd(Constants.NO);
            cacheClient.put(Constants.CACHE_GROUP, Constants.CacheKey.USER_INFO_PREFIX_KEY + getCurrentUserId(), JSONUtils.objectToString(getCurrentUser()));
        }
        return res;
    }
}
