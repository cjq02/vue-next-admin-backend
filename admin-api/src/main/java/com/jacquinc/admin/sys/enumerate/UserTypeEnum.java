package com.jacquinc.admin.sys.enumerate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengzheng on 2019/6/5.
 */
public enum UserTypeEnum {

    /**
     * 1-超级管理员
     */
    SUPER_ADMIN("1", "超级管理员"),

    /**
     * 2-项目负责人
     */
    USER_TYPE_2("2", "项目负责人"),

    /**
     * 3-精益办成员
     */
    USER_TYPE_3("3", "精益办成员"),

    /**
     * 4-专家
     */
    USER_TYPE_4("4", "专家"),
    ;

    private String name;

    private String code;

    UserTypeEnum(String code, String name) {
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
            for (UserTypeEnum enumerate : UserTypeEnum.values()) {
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
            for (UserTypeEnum enumerate : UserTypeEnum.values()) {
                map.put(enumerate.getCode(), enumerate.getName());
            }
        }
        return map;
    }

    public static UserTypeEnum getByCode(String code) {
        UserTypeEnum _enum = null;
        for (UserTypeEnum enumerate : UserTypeEnum.values()) {
            if (enumerate.getCode().equals(code)) {
                _enum = enumerate;
                break;
            }
        }
        return _enum;
    }
}
