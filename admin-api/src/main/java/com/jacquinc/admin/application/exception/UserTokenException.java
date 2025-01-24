package com.jacquinc.admin.application.exception;


import com.jacquinc.admin.application.constants.Constants;

/**
 * Created by zhengzheng on 2018/5/4.
 */
public class UserTokenException extends RuntimeException {

    private String code;
    private boolean show;
    private Object data;

    public UserTokenException() {
        super(Constants.ErrorCode.USER_TOKEN_ERROR.getMessage());
        this.code = Constants.ErrorCode.USER_TOKEN_ERROR.getCode();
    }

    public UserTokenException(boolean show, Object data) {
        super(Constants.ErrorCode.USER_TOKEN_ERROR.getMessage());
        this.code = Constants.ErrorCode.USER_TOKEN_ERROR.getCode();
        this.show = show;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}