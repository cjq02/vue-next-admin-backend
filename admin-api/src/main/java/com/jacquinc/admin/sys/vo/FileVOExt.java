package com.jacquinc.admin.sys.vo;

import io.swagger.annotations.ApiModelProperty;

public class FileVOExt extends FileVO {

    @ApiModelProperty("旧id")
    private String oldId;

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }
}