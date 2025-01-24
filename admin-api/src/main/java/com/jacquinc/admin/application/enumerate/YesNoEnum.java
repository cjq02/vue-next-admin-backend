package com.jacquinc.admin.application.enumerate;

import com.google.common.collect.Maps;
import com.jacquinc.admin.application.annotation.EnumCodeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EnumCodeInfo(typeName = "是否")
public enum YesNoEnum {

    /**
     * 1 是
     */
    YES("1", "是"),
    /**
     * 0 否
     */
    NO("0", "否"),
    ;

    private String code;
    private String name;

    YesNoEnum(String code, String name) {
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

    private static Map<String, Object> map = Maps.newHashMap();

    /**
     * 转换成List<Map>
     * JSON类似：[{code:1,name:"名称1"},{code:2,name:"名称2"},{code:3,name:"名称3"}...]
     */
    public static List<Map<String, Object>> toList() {
        if (list.isEmpty()) {
            for (YesNoEnum enumerate : YesNoEnum.values()) {
                Map<String, Object> map = Maps.newHashMap();
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
            for (YesNoEnum enumerate : YesNoEnum.values()) {
                map.put(enumerate.getCode(), enumerate.getName());
            }
        }
        return map;
    }

    public static YesNoEnum getByCode(String code) {
        YesNoEnum _enum = null;
        for (YesNoEnum enumerate : YesNoEnum.values()) {
            if (enumerate.getCode().equals(code)) {
                _enum = enumerate;
                break;
            }
        }
        return _enum;
    }

    public static YesNoEnum getByName(String name) {
        for (YesNoEnum enumerate : YesNoEnum.values()) {
            if (enumerate.getName().equals(name)) {
                return enumerate;
            }
        }
        return null;
    }
}
