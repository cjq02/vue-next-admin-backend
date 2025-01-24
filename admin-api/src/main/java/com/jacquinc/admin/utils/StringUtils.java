package com.jacquinc.admin.utils;


import com.google.common.base.CaseFormat;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;

/**
 * @author cjq
 * created on  2019/11/18
 */
public class StringUtils extends com.jiujie.framework.base.utils.StringUtils {

    private final static int INCLUDE_REPLACE_PARAM_LENGTH = 2;

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String && ((String) value).trim().length() <= 0) {
            return true;
        } else if (value instanceof Object[] && ((Object[]) ((Object[]) value)).length <= 0) {
            return true;
        } else if (value instanceof Object[]) {
            Object[] t = (Object[]) ((Object[]) value);

            for (int i = 0; i < t.length; ++i) {
                if (t[i] != null) {
                    return false;
                }
            }

            return true;
        } else {
            return value instanceof Collection && ((Collection) value).size() <= 0 || (value instanceof Dictionary && ((Dictionary) value).size() <= 0 || value instanceof Map && ((Map) value).size() <= 0);
        }
    }

    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    /**
     * 不为空返回原值，如果为空，则替换为指定值，若未设定指定值则返回空字符
     *
     * @param params param1: 原值，param2：替换值
     * @return 值
     */
    public static String ifEmpty(String... params) {
        String origin = params[0];
        String replace = "";

        if (params.length == INCLUDE_REPLACE_PARAM_LENGTH) {
            replace = params[1];
        }

        if (StringUtils.isNotEmpty(origin)) {
            return origin;
        }

        return replace;
    }

    public static String camelCaseToSnakeCase(String camelCaseStr) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, camelCaseStr);
    }
}
