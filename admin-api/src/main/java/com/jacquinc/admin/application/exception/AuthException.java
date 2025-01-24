package com.jacquinc.admin.application.exception;

import com.jacquinc.admin.application.constants.Constants;

public class AuthException extends RuntimeException {

    private String code;

    public AuthException() {
        super(Constants.ErrorCode.USER_AUTH_ERROR.getMessage());
        this.code = Constants.ErrorCode.USER_AUTH_ERROR.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
