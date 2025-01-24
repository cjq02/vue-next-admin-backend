package com.jacquinc.admin.sys.enumerate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengzheng on 2019/9/6.
 */
public enum CorpTypeEnum {

    /**
     * 1-集团公司
     */
    TYPE_1("1", "集团公司", "3", "3"),

    /**
     * 2-板块公司
     */
    TYPE_2("2", "板块公司", "2", "2"),

    /**
     * 3-三级单位
     */
    TYPE_3("3", "三级单位", "1", "1");

    private String name;
    private String code;
    private String projectSign;
    private String expertLevel;

    CorpTypeEnum(String code, String name, String projectSign, String expertLevel) {
        this.code = code;
        this.name = name;
        this.projectSign = projectSign;
        this.expertLevel = expertLevel;
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

    public String getProjectSign() {
        return projectSign;
    }

    public void setProjectSign(String projectSign) {
        this.projectSign = projectSign;
    }

    public String getExpertLevel() {
        return expertLevel;
    }

    public void setExpertLevel(String expertLevel) {
        this.expertLevel = expertLevel;
    }

    private static List<Map<String, Object>> list = new ArrayList<>();

    private static Map<String, Object> map = new HashMap<>();

    /**
     * 转换成List<Map>
     * JSON类似：[{code:1,name:"名称1"},{code:2,name:"名称2"},{code:3,name:"名称3"}...]
     */
    public static List<Map<String, Object>> toList() {
        if (list.isEmpty()) {
            for (CorpTypeEnum enumerate : CorpTypeEnum.values()) {
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
            for (CorpTypeEnum enumerate : CorpTypeEnum.values()) {
                map.put(enumerate.getCode(), enumerate.getName());
            }
        }
        return map;
    }

    public static CorpTypeEnum getByCode(String code) {
        CorpTypeEnum _enum = null;
        for (CorpTypeEnum enumerate : CorpTypeEnum.values()) {
            if (enumerate.getCode().equals(code)) {
                _enum = enumerate;
                break;
            }
        }
        return _enum;
    }

    public static CorpTypeEnum getByExpertLevel(String expertLevel) {
        CorpTypeEnum _enum = null;
        for (CorpTypeEnum enumerate : CorpTypeEnum.values()) {
            if (enumerate.getExpertLevel().equals(expertLevel)) {
                _enum = enumerate;
                break;
            }
        }
        return _enum;
    }
}
