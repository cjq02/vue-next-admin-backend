package com.jacquinc.admin.application.bo;

import com.jiujie.framework.adapter.vo.ResponseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhengzheng on 2020/11/17.
 */
@ApiModel(value = "list返回", description = "返回list数据内容")
public class ResponseResultListBO<T> extends ResponseResult {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否下一页", position = 4)
    private boolean hasNext = false;

    @ApiModelProperty(value = "数据", position = 5)
    private List<T> list;

    public ResponseResultListBO() {
        super();
    }

    /**
     * @param list    数组对象
     */
    public ResponseResultListBO(List<T> list) {
        this.list = list;
    }

    /**
     * @param success 是否成功
     * @param code    响应结果码
     * @param message 消息字符
     * @param list    数组对象
     */
    public ResponseResultListBO(boolean success, String code, String message, List<T> list) {
        super(success, code, message);
        this.list = list;
    }

    /**
     * @param code    响应结果码
     * @param message 消息字符
     * @param list    数组对象
     */
    public ResponseResultListBO(String code, String message, List<T> list) {
        super(code, message);
        this.list = list;
    }

    /**
     * @param message 消息字符
     * @param list    数组对象
     */
    public ResponseResultListBO(String message, List<T> list) {
        super(message);
        this.list = list;
    }

    /**
     * @param success 是否成功
     * @param list    数组对象
     */
    public ResponseResultListBO(boolean success, List<T> list) {
        super(success);
        this.list = list;
    }

    /**
     * @param success 是否成功
     * @param code    响应结果码
     * @param list    数组对象
     */
    public ResponseResultListBO(boolean success, String code, List<T> list) {
        super(success, code);
        this.list = list;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
