package com.jacquinc.admin.application.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zhengzheng on 2020/11/17.
 */
@ApiModel(value = "bean返回", description = "返回单一数据内容")
public class ResponseResultBO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回状态", position = 1)
    private boolean success = true;

    @ApiModelProperty(value = "返回编码", position = 2)
    private String code;

    @ApiModelProperty(value = "返回信息", position = 3)
    private String message;

    @ApiModelProperty(value = "数据", position = 4)
    private T info;
    
    public ResponseResultBO() {
    }

    /**
     * @param success 是否成功
     * @param code    响应结果码
     * @param message 消息字符
     * @param info    信息对象
     */
    public ResponseResultBO(boolean success, String code, String message, T info) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.info = info;
    }

    /**
     * @param success 是否成功
     * @param code    响应结果码
     * @param message 消息字符
     */
    public ResponseResultBO(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * @param success 是否成功
     * @param code    响应结果码
     */
    public ResponseResultBO(boolean success, String code) {
        this.success = success;
        this.code = code;
    }

    /**
     * @param code    响应结果码
     * @param message 消息字符
     * @param info    信息对象
     */
    public ResponseResultBO(String code, String message, T info) {
        this.code = code;
        this.message = message;
        this.info = info;
    }

    /**
     * @param message 消息字符
     * @param info    信息对象
     */
    public ResponseResultBO(String message, T info) {
        this.message = message;
        this.info = info;
    }

    /**
     * @param info    信息对象
     */
    public ResponseResultBO(T info) {
        this.info = info;
    }

    /**
     * @param code    响应结果码
     * @param message 消息字符
     */
    public ResponseResultBO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @param success 是否成功
     * @param info    信息对象
     */
    public ResponseResultBO(boolean success, T info) {
        this.success = success;
        this.info = info;
    }

    /**
     * @param success 是否成功
     * @param code    响应结果码
     * @param info    信息对象
     */
    public ResponseResultBO(boolean success, String code, T info) {
        this.success = success;
        this.code = code;
        this.info = info;
    }

    /**
     * @param message    消息字符
     */
    public ResponseResultBO(String message) {
        this.message = message;
    }

    /**
     * 消息字符
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 消息字符
     *
     * @param message
     * @return
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 是否成功
     *
     * @param success
     * @return
     */
    public ResponseResultBO<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 信息对象（xml、object、jsonObject、List 等）
     *
     * @return
     */
    public T getInfo() {
        return info;
    }

    /**
     * 信息对象（xml、object、jsonObject、List 等）
     *
     * @param info
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     * 响应结果码
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 响应结果码
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
