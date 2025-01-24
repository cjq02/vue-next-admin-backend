package com.jacquinc.admin.sys.vo;

import java.util.List;

public class CodeVOExt extends CodeVO {

    /**
     * 变更状态
     */
    private String modifyStatus;

    /**
     * 枚举包路径
     */
    private String packagePath;

    /**
     * 枚举类名
     */
    private String enumClassName;

    /**
     * 是否有备注
     */
    private String hasRemark;

    /**
     * 是否动态配置
     */
    private String isDynamicConfig;

    /**
     * 是否枚举
     */
    private String isEnum;

    private List<CodeVO> codeList;

    public CodeVOExt() {
    }

    public CodeVOExt(String typeCode) {
        setTypeCode(typeCode);
    }

    public String getModifyStatus() {
        return modifyStatus;
    }

    public void setModifyStatus(String modifyStatus) {
        this.modifyStatus = modifyStatus;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getEnumClassName() {
        return enumClassName;
    }

    public void setEnumClassName(String enumClassName) {
        this.enumClassName = enumClassName;
    }

    public String getHasRemark() {
        return hasRemark;
    }

    public void setHasRemark(String hasRemark) {
        this.hasRemark = hasRemark;
    }

    public String getIsDynamicConfig() {
        return isDynamicConfig;
    }

    public void setIsDynamicConfig(String isDynamicConfig) {
        this.isDynamicConfig = isDynamicConfig;
    }

    public String getIsEnum() {
        return isEnum;
    }

    public void setIsEnum(String isEnum) {
        this.isEnum = isEnum;
    }

    public List<CodeVO> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<CodeVO> codeList) {
        this.codeList = codeList;
    }
}
