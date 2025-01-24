package com.jacquinc.admin.sys.vo;

import lombok.NonNull;

/**
 * @author cjq
 * created on  2020/09/01
 */
public class DepartmentVOExt extends DepartmentVO {

    private String parentName;
    private String typeName;

    // 导出需要字段
    private String corpName;

    public static DepartmentVOExt createByImportCriteria(String id, @NonNull String corpId, @NonNull String code,
                                                         @NonNull String name, @NonNull String nickName, @NonNull String type) {
        DepartmentVOExt vo = new DepartmentVOExt();
        vo.setId(id);
        vo.setCorpId(corpId);
        vo.setCode(code);
        vo.setName(name);
        vo.setNickName(nickName);
        vo.setType(type);
        return vo;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }
}