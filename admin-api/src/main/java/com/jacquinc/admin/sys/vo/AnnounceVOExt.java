package com.jacquinc.admin.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AnnounceVOExt extends AnnounceVO {

    @ApiModelProperty(value = "状态名称")
    private String statusName;

    @ApiModelProperty(value = "0：可编辑；1：预览")
    private String isView;
}
