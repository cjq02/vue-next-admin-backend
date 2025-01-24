package com.jacquinc.admin.sys.enumerate;

import com.jacquinc.admin.application.annotation.EnumCodeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cjq
 * created on  2020/08/18.
 */
@EnumCodeInfo(typeName = "码表枚举匹配状态")
public enum CodeModifyStatusEnum {


    ADD("1", "枚举新增", "success"),
    UPDATE("2", "枚举修改", "warning"),
    GENERATED("3", "已同步数据", "green"),
    NORMAL("4", "枚举未改动", "black"),
    ENUM_NOT_EXISTS("5", "仅存在码表", "gray"),
    ;

    private String name;
    private String code;
    private String remark;

    CodeModifyStatusEnum(String code, String name, String remark) {
        this.code = code;
        this.name = name;
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private static List<Map<String, Object>> list = new ArrayList<>();

    private static Map<String, Object> map = new HashMap<>();

    /**
     * 转换成List<Map>
     * JSON类似：[{code:1,name:"名称1"},{code:2,name:"名称2"},{code:3,name:"名称3"}...]
     */
    public static List<Map<String, Object>> toList() {
        if (list.isEmpty()) {
            for (CodeModifyStatusEnum enumerate : CodeModifyStatusEnum.values()) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", enumerate.getName());
                map.put("code", enumerate.getCode());
                map.put("remark", enumerate.getRemark());
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
            for (CodeModifyStatusEnum enumerate : CodeModifyStatusEnum.values()) {
                map.put(enumerate.getCode(), enumerate.getName());
            }
        }
        return map;
    }

    public static CodeModifyStatusEnum getByCode(String code) {
        CodeModifyStatusEnum _enum = null;
        for (CodeModifyStatusEnum enumerate : CodeModifyStatusEnum.values()) {
            if (enumerate.getCode().equals(code)) {
                _enum = enumerate;
                break;
            }
        }
        return _enum;
    }

}
