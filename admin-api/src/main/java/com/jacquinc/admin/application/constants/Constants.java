package com.jacquinc.admin.application.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 常量类
 *
 * @author cjq 2019-09-10
 */
public class Constants {

    // www.sports-reimburse.com md5
    public static final String SIGN_KEY = "BEDB419F753EF26DB16036F2AC944611";
    // sports-reimburse md5
    public static final String JWT_SECRET_KEY = "DD1179E4E649220327D0BD3F8AE0EE38";

    // 静态变量
    public static final String YES = "1";
    public static final String NO = "0";
    public static final String YES_STR = "是";
    public static final String NO_STR = "否";
    public static final int PAGE_SIZE = 10;

    /**
     * 本地环境标识
     */
    public final static String LOCAL = "local";

    public static final String PACKAGE_PREFIX = "com.jacquinc.admin";

    /**
     * 超管用户ID
     */
    public static final String ADMIN_USER_ID = "1";

    /**
     * session attribute 用户
     */
    public static final String SESSION_USER = "user";
    public static final String REQUEST_PARAMS = "REQUEST_PARAMS";

    /**
     * session attribute device
     */
    public static final String SESSION_DEVICE = "device";
    public static final String DEVICE_ANDROID = "android";
    public static final String DEVICE_IOS = "ios";

    public static final String MENU_ROOT_ID = "1";
    public static final String MENU_ROOT_NAME = "顶级菜单";

    public static final List<String> GENERATE_CODE_IGNORE_LIST = Arrays.asList(
            "TEST_STATUS",
            "BUDGET_YEAR"
    );
    public static final List<String> GENERATE_CODE_IGNORE_LIKE_LIST = Arrays.asList(
            "SYS_PARAM_",
            "SYSTEM_PARAM_"
    );
    /**
     * 码表缓存
     */
    public static final String CODE_LIST_CACHE = "codeCache";

    /**
     * memcached组
     */
    public static final String CACHE_GROUP = "redis1";

    public static class CacheKey {
        public static final String LOGIN_VALIDATE_CODE_PREFIX_KEY = "validate_";
        public static final String LOGIN_RSA_PREFIX_KEY = "user_rsa_";
        public static final String LOGIN_FAIL_PREFIX_KEY = "appLoginFailureCount_";
        public static final String USER_TOKEN_PREFIX_KEY = "user_";
        public static final String USER_INFO_PREFIX_KEY = "user_info_";

        public static final String APP_USER_TOKEN_PREFIX_KEY = "app_user_";
        public static final String APP_USER_INFO_PREFIX_KEY = "app_user_info_";
        public static final String USER_SMS_PREFIX_KEY = "msgId_";

        public static final String CODE_PREFIX_KEY = "code_";
        public static final String USER_APP_UPDATE_KEY = "user_app_update_";
    }

    public static class User {
        public static final int LOGIN_FAIL_COUNT = 5; //用户登录失败次数
        public static final long LOGIN_FAIL_TIME_LIMIT = 900; //用户登录失败超过次数后，等待时间 秒
        public static final int TOKEN_TIMEOUT = 1000 * 60 * 60 * 24 * 7; //token 7天失效
    }

    // 参数错误通用返回结果
    public enum ErrorCode {
        // app级
        UPDATE_ALERT("0000", ""),
        UPDATE_FORCED_ALERT("0001", ""),

        // 系统级
        PARAMS_NULL_ERROR("1000", "参数错误，请联系客服人员。"),
        PARAMS_SIGN_ERROR("1001", "参数签名错误，请联系客服人员。"),
        USER_TOKEN_ERROR("1010", "用户登录状态失效，请重新登录！"),
        USER_AUTH_ERROR("1011", "操作失败，该用户没有权限！"),
        ;

        private String code;
        private String message;

        ErrorCode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
