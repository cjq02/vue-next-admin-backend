package com.jacquinc.admin.application.exception;


import com.jacquinc.admin.application.constants.Constants;

/**
 * Created by zhengzheng on 2020/11/16.
 */
public class ValidateException extends RuntimeException {

    private static final Long serialVersionUID = 4331563802763038864L;

    private String code;

    private Boolean isShow;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    public ValidateException() {
        super();
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    public ValidateException(Constants.ErrorCode validateCode, boolean isShow) {
        super(validateCode.getMessage());
        this.code = validateCode.getCode();
        this.isShow = isShow;
    }
}
