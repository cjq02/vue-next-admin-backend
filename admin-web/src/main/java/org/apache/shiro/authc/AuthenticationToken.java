package org.apache.shiro.authc;

import java.io.Serializable;

/**
 * Created by zhengzheng on 2020/9/19.
 */
public interface AuthenticationToken extends Serializable {

    Object getPrincipal();

    Object getCredentials();

    String getCaptcha();
}
