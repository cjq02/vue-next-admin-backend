package com.jacquinc.admin.application.vo;

import com.jiujie.framework.base.vo.BaseVO;

@SuppressWarnings("unused")
public class EnumVO extends BaseVO {

    /**
     * 键值
     */
    private String key;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    private String parentCode;

    /**
     * 备注
     */
    private String remark;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
