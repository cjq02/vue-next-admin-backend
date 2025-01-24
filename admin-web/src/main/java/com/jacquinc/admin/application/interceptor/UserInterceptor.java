package com.jacquinc.admin.application.interceptor;

import com.jacquinc.admin.application.utils.IpUtils;
import com.jacquinc.admin.application.utils.JWTUtils;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.application.bo.DeviceBO;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.exception.AuthException;
import com.jacquinc.admin.application.exception.UserTokenException;
import com.jacquinc.admin.sys.service.IUserService;
import com.jacquinc.admin.sys.vo.UserVOExt;
import com.jacquinc.admin.utils.DateUtils;
import com.jiujie.framework.base.utils.JSONUtils;
import com.jiujie.framework.cache.cache.ICacheClient;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.spring.context.utils.SpringContextUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private ICacheClient cacheClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println(request.getRequestURL());
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            return true;
        }
        DeviceBO deviceBO = (DeviceBO) request.getSession().getAttribute(Constants.SESSION_DEVICE);
        String deviceUUId = deviceBO.getDeviceuuid();
        if (StringUtils.isEmpty(deviceUUId)) {
            throw new UserTokenException();
        }
        String jwt = request.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)) {
            //如果header没有 则去cookies里面取，再没有报错
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }
            if (StringUtils.isEmpty(jwt)) {
                throw new UserTokenException();
            }
        }
        // 验证token是否有效
        Claims claims;
        try {
            claims = JWTUtils.parseJWT(Constants.JWT_SECRET_KEY, jwt);
        } catch (Exception e) {
            throw new UserTokenException();
        }
        String id = claims.getSubject();
        String userCacheJwt = (String) cacheClient.get(Constants.CACHE_GROUP, Constants.CacheKey.USER_TOKEN_PREFIX_KEY + id + deviceUUId);
        if (StringUtils.isBlank(userCacheJwt)) {
            throw new UserTokenException();
        }
        UserVOExt user = JSONUtils.stringToBean(cacheClient.get(Constants.CACHE_GROUP,
                Constants.CacheKey.USER_INFO_PREFIX_KEY + id).toString(), UserVOExt.class);
        if (Constants.NO.equals(user.getActive())) {
            throw new BusinessException("用户被锁定，请联系客服！");
        }
        if (userCacheJwt.equals(jwt)) {
            if (claims.getExpiration().before(DateUtils.getCurrentDate())) {
                throw new UserTokenException();
            }
            // 判断jwt是否临期
            if (DateUtils.subDay(claims.getExpiration(), 1).before(DateUtils.getCurrentDate())) {
                String remoteIp = IpUtils.getRemoteIp4(request);
                String newJwt = userLoginInfo(user, deviceUUId, remoteIp);
                response.setHeader("Authorization", newJwt);
            }
        } else {
            response.setHeader("Authorization", userCacheJwt);
        }
        request.getSession().setAttribute(Constants.SESSION_USER, user);
        // 校验接口权限
        HandlerMethod handlerMethod = (HandlerMethod) o;
        HasPermission annotationInMethod = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), HasPermission.class);
        if (null != annotationInMethod && !annotationInMethod.value().equals("user") && !user.getPermissions().contains(annotationInMethod.value())) {
            throw new AuthException();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }

    public static String userLoginInfo(UserVOExt user, String deviceUUID, String remoteIp) throws Exception {
        SpringContextUtil.getBean(IUserService.class).updateLoginData(user.getId(), remoteIp);

        // 生成jwt，放入redis 用于之后对比
        ICacheClient cacheClient = SpringContextUtil.getBean(ICacheClient.class);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", user.getId());
        claims.put("realName", user.getRealName());
        claims.put("phone", user.getPhone());
        String jwt = JWTUtils.createJWT(deviceUUID, user.getId(), Constants.User.TOKEN_TIMEOUT, Constants.JWT_SECRET_KEY, claims);
        cacheClient.put(Constants.CACHE_GROUP, Constants.CacheKey.USER_TOKEN_PREFIX_KEY + user.getId() + deviceUUID, jwt);

        cacheClient.put(Constants.CACHE_GROUP, Constants.CacheKey.USER_INFO_PREFIX_KEY + user.getId(), JSONUtils.objectToString(user));
        return jwt;
    }
}
