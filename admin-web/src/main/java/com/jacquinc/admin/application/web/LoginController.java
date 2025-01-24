package com.jacquinc.admin.application.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jacquinc.admin.application.utils.IpUtils;
import com.jacquinc.admin.application.utils.ValidateCodeUtil;
import com.jacquinc.admin.common.util.RsaEncryptKeyUtil;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.application.bo.*;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.interceptor.UserInterceptor;
import com.jacquinc.admin.sys.service.IUserService;
import com.jacquinc.admin.sys.vo.UserVOExt;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.cache.cache.ICacheClient;
import com.jiujie.framework.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
@RestController
@Api(tags = "登录")
@ApiSort(1)
public class LoginController extends BaseController {

    @Autowired
    private ICacheClient cacheClient;

    @Autowired
    private IUserService userService;

    @GetMapping("/getEncryptKey.json")
    @ApiOperation("生成RSA秘钥")
    @ApiOperationSupport(order = 1)
    public ResponseResultBO<RsaBO> getEncryptKey(HttpServletRequest request) {
        DeviceBO deviceBO = (DeviceBO) getRequest().getSession().getAttribute(Constants.SESSION_DEVICE);
        return new ResponseResultBO<>("生成RSA秘钥成功！", RsaEncryptKeyUtil.getEncryptKey(deviceBO.getDeviceuuid()));
    }

    @PostMapping("/login.json")
    @ApiOperation("密码登录")
    @ApiOperationSupport(order = 2)
    public ResponseResultBO<UserTokenBO> passwordLogin(@RequestBody UserBO bo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数验证
        if (StringUtils.isEmpty(bo.getUsername()) || StringUtils.isEmpty(bo.getPassword())) {
            throw new BusinessException("账号，密码不能为空！");
        }
        DeviceBO deviceBO = (DeviceBO) getRequest().getSession().getAttribute(Constants.SESSION_DEVICE);
        String failKey = Constants.CacheKey.LOGIN_FAIL_PREFIX_KEY + deviceBO.getDeviceuuid();
        if (cacheClient.isExist(Constants.CACHE_GROUP, failKey)) {
            int failCount = (int) cacheClient.get(Constants.CACHE_GROUP, failKey);
            if (failCount > 3 && (StringUtils.isEmpty(bo.getValidateCode()) ||
                    !cacheClient.get(Constants.CACHE_GROUP, Constants.CacheKey.LOGIN_VALIDATE_CODE_PREFIX_KEY + deviceBO.getDeviceuuid()).toString().equals(bo.getValidateCode()))) {
                throw new BusinessException("验证码未填写或不一致！");
            }
        }
        String password = RsaEncryptKeyUtil.decrypt(deviceBO.getDeviceuuid(), bo.getPassword());
        logger.warn("deviceuuid: {}, rsa_password: {}, password: {}", deviceBO.getDeviceuuid(), bo.getPassword(), password);
        if (StringUtils.isEmpty(password)) {
            loginFail(deviceBO.getDeviceuuid());
            throw new BusinessException("密码校验失败！请联系管理员！");
        }
        bo.setPassword(password);
        UserVOExt userVOExt = userService.loginCheck(bo.getUsername(), bo.getPassword(), deviceBO.getDeviceuuid());
        String remoteIp = IpUtils.getRemoteIp4(getRequest());
        String jwt = UserInterceptor.userLoginInfo(userVOExt, deviceBO.getDeviceuuid(), remoteIp);
        response.setHeader("Authorization", jwt);
        return new ResponseResultBO<>("用户登录成功！");
    }

    @ApiOperation("登录失败次数")
    @ApiOperationSupport(order = 3)
    @GetMapping(value = "/getLoginFailCount.json")
    public int getLoginFailCount() {
        DeviceBO deviceBO = (DeviceBO) getRequest().getSession().getAttribute(Constants.SESSION_DEVICE);
        String failKey = Constants.CacheKey.LOGIN_FAIL_PREFIX_KEY + deviceBO.getDeviceuuid();
        if (cacheClient.isExist(Constants.CACHE_GROUP, failKey)) {
            return (int) cacheClient.get(Constants.CACHE_GROUP, failKey);
        }
        return 0;
    }

    private void loginFail(String deviceuuid) {
        String failKey = Constants.CacheKey.LOGIN_FAIL_PREFIX_KEY + deviceuuid;
        if (cacheClient.isExist(Constants.CACHE_GROUP, failKey)) {
            cacheClient.put(Constants.CACHE_GROUP, failKey, (int) cacheClient.get(Constants.CACHE_GROUP, failKey) + 1);
        } else {
            cacheClient.put(Constants.CACHE_GROUP, failKey, 1);
        }
    }

    @GetMapping("/validateCode.json")
    @ApiOperation(value = "图形验证码")
    @ApiOperationSupport(order = 4)
    public ResponseResultBO validateCode() throws IOException {
        DeviceBO deviceBO = (DeviceBO) getRequest().getSession().getAttribute(Constants.SESSION_DEVICE);
        return new ResponseResultBO(null, ValidateCodeUtil.generator(deviceBO.getDeviceuuid()));
    }

    @HasPermission("user")
    @RequestMapping(value = "/logout.json", method = RequestMethod.GET)
    @ApiOperation("登出")
    @ApiOperationSupport(order = 5)
    public ResponseResultBO<String> logout(HttpServletRequest request) {
        UserVOExt user = getCurrentUser();
        if (user != null) {
            DeviceBO deviceBO = (DeviceBO) getRequest().getSession().getAttribute(Constants.SESSION_DEVICE);
            cacheClient.remove(Constants.CACHE_GROUP, Constants.CacheKey.USER_TOKEN_PREFIX_KEY + getCurrentUser().getId() + deviceBO.getDeviceuuid());
        }
        return new ResponseResultBO<>("登出成功！", null);
    }
}
