package com.jacquinc.admin.application.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by zhengzheng on 2020/11/16.
 */
@ApiModel("用户信息")
@Data
public class UserTokenBO {

    @ApiModelProperty(position = 1, value = "token")
    private String jwt;
    @ApiModelProperty(position = 2, value = "用户id")
    private String id;
    @ApiModelProperty(position = 3, value = "用户姓名")
    private String realName;
    @ApiModelProperty(position = 4, value = "用户手机")
    private String phone;

    @ApiModelProperty(position = 5, value = "角色类型列表")
    private List<String> roleTypeList;
}
