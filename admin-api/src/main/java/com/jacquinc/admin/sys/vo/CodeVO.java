package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/** 自动生成的VO,请不要修改 */
public class CodeVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "字典分类代码，如产品类型代码PRODUCT_TYPE")
    private String typeCode;

    @ApiModelProperty(value = "字典分类名称，如产品类型")
    private String typeName;

    @ApiModelProperty(value = "列表的中文值，如贷款")
    private String configName;

    @ApiModelProperty(value = "列表值的编码，如LB")
    private String configCode;

    @ApiModelProperty(value = "列表中显示的顺序号，如1,2,3")
    private Short indexNo;

    @ApiModelProperty(value = "描述或备注信息")
    private String remark;

    @ApiModelProperty(value = "创建人id")
    private String createUserId;

    @ApiModelProperty(value = "创建日期")
    private Date createTs;

    @ApiModelProperty(value = "更新人id")
    private String updateUserId;

    @ApiModelProperty(value = "更新日期")
    private Date updateTs;

    @ApiModelProperty(value = "是否默认（如默认选中、默认展示等）：0-否 1-是")
    private String isDefault;

    @ApiModelProperty(value = "父编码")
    private String parentCode;



    /**
     * id
     */
    public String getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 字典分类代码，如产品类型代码PRODUCT_TYPE
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 字典分类代码，如产品类型代码PRODUCT_TYPE
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 字典分类名称，如产品类型
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 字典分类名称，如产品类型
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 列表的中文值，如贷款
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * 列表的中文值，如贷款
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * 列表值的编码，如LB
     */
    public String getConfigCode() {
        return configCode;
    }

    /**
     * 列表值的编码，如LB
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    /**
     * 列表中显示的顺序号，如1,2,3
     */
    public Short getIndexNo() {
        return indexNo;
    }

    /**
     * 列表中显示的顺序号，如1,2,3
     */
    public void setIndexNo(Short indexNo) {
        this.indexNo = indexNo;
    }

    /**
     * 描述或备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 描述或备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 创建人id
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人id
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 创建日期
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * 创建日期
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * 更新人id
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人id
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 更新日期
     */
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * 更新日期
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    /**
     * 是否默认（如默认选中、默认展示等）：0-否 1-是
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 是否默认（如默认选中、默认展示等）：0-否 1-是
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 父编码
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 父编码
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

}