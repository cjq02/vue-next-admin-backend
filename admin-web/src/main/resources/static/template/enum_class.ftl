package ${package};

import ${packagePrefix}.application.annotation.EnumCodeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Auto Generate
 * created on  ${date}
 */
@EnumCodeInfo(typeCode = "${typeCode}", typeName = "${typeName}")
public enum ${enumClassName} {

    <#list list as item>
    ${item.key}("${item.code}", "${item.name}"<#if hasRemark>, "${item.remark}"</#if>),
    </#list>
    ;

    private String name;

    private String code;

    <#if hasRemark>
    private String remark;
    </#if>

    ${enumClassName}(String code, String name<#if hasRemark>, String remark</#if>) {
        this.code = code;
        this.name = name;
        <#if hasRemark>
        this.remark = remark;
        </#if>
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

    <#if hasRemark>
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    </#if>

    private static List<Map<String, Object>> list = new ArrayList<>();

    private static Map<String, Object> map = new HashMap<>();

    /**
     * 转换成List<Map>
     * JSON类似：[{code:1,name:"名称1"},{code:2,name:"名称2"},{code:3,name:"名称3"}...]
     */
    public static List<Map<String, Object>> toList() {
        if (list.isEmpty()) {
            for (${enumClassName} enumerate : ${enumClassName}.values()) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", enumerate.getName());
                map.put("code", enumerate.getCode());
                <#if hasRemark>
                map.put("remark", enumerate.getRemark());
                </#if>
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
            for (${enumClassName} enumerate : ${enumClassName}.values()) {
                map.put(enumerate.getCode(), enumerate.getName());
            }
        }
        return map;
    }

    public static ${enumClassName} getByCode(String code) {
        ${enumClassName} _enum = null;
        for (${enumClassName} enumerate : ${enumClassName}.values()) {
            if (enumerate.getCode().equals(code)) {
                _enum = enumerate;
                break;
            }
        }
        return _enum;
    }
}