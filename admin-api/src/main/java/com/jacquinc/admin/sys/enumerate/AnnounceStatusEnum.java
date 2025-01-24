package com.jacquinc.admin.sys.enumerate;

import com.jacquinc.admin.application.annotation.EnumCodeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Auto Generate
 * created on  2022-06-30
 */
@EnumCodeInfo(typeCode = "ANNOUNCE_STATUS", typeName = "公告状态")
public enum AnnounceStatusEnum {

    _0("0", "未发布"),
    _1("1", "已发布"),
    ;

    private String name;

    private String code;


    AnnounceStatusEnum(String code, String name) {
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
            for (AnnounceStatusEnum enumerate : AnnounceStatusEnum.values()) {
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
            for (AnnounceStatusEnum enumerate : AnnounceStatusEnum.values()) {
                map.put(enumerate.getCode(), enumerate.getName());
            }
        }
        return map;
    }

    public static AnnounceStatusEnum getByCode(String code) {
        AnnounceStatusEnum _enum = null;
        for (AnnounceStatusEnum enumerate : AnnounceStatusEnum.values()) {
            if (enumerate.getCode().equals(code)) {
                _enum = enumerate;
                break;
            }
        }
        return _enum;
    }
}