package com.jacquinc.admin.sys.enumerate;

import com.jacquinc.admin.application.annotation.EnumCodeInfo;
import com.jiujie.framework.base.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cjq
 * created on  2021/03/08
 */
@EnumCodeInfo(typeName = "角色类型")
public enum RoleTypeEnum {

    _01("01", "超级管理员"),
    _02("02", "普通管理员"),
    _09("09", "普通用户"),
    ;
    private String name;

    private String code;

    RoleTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private static List<Map<String, Object>> list = new ArrayList<>();

    private static Map<String, Object> map = new HashMap<>();

    /**
     * 转换成List<Map>
     * JSON类似：[{code:1,name:"名称1"},{code:2,name:"名称2"},{code:3,name:"名称3"}...]
     */
    public static List<Map<String, Object>> toList() {
        if (list.isEmpty()) {
            for (RoleTypeEnum enumerate : RoleTypeEnum.values()) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", enumerate.getName());
                map.put("code", enumerate.getCode());
                //
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 转换成Map
     * JSON类似：{"1":"名称1","2":"名称2","3":"名称3"}
     */
    public static Map<String, Object> toMap() {
        if (map.isEmpty()) {
            for (RoleTypeEnum enumerate : RoleTypeEnum.values()) {
                map.put(enumerate.getCode(), enumerate.getName());
            }
        }
        return map;
    }

    public static RoleTypeEnum getByCode(String code) {
        RoleTypeEnum _enum = null;
        for (RoleTypeEnum enumerate : RoleTypeEnum.values()) {
            if (enumerate.getCode().equals(code)) {
                _enum = enumerate;
                break;
            }
        }
        return _enum;
    }

    public static String toStr(String isAdmin) {
        List<String> codes = Arrays.asList(isAdmin.split(","));
        List<RoleTypeEnum> enums = new ArrayList<>();
        codes.forEach(code -> {
            RoleTypeEnum roleTypeEnum = getByCode(code);
            if (roleTypeEnum != null) {
                enums.add(roleTypeEnum);
            }
        });
        if (!enums.isEmpty()) {
            List<String> names = enums.stream().map(RoleTypeEnum::getName).collect(Collectors.toList());
            return StringUtils.join(names, ",");
        }
        return "";
    }
}
