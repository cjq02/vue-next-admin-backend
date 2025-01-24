package com.jacquinc.admin.application.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zhengzheng on 2020/11/20.
 */
@ApiModel("用户")
public class UserBO implements Serializable {

    private static final long serialVersionUID = 1L;

    // post参数
    @ApiModelProperty(position = 1, value = "手机号")
    private String phone;
    @ApiModelProperty(position = 2, value = "密码")
    private String password;
    @ApiModelProperty(position = 3, value = "图形验证码")
    private String validateCode;

    @ApiModelProperty(position = 4, value = "用户名")
    private String username;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
