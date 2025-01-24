package com.jacquinc.admin.sys.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CorpVOExt extends CorpVO {

    /**
     * 企业ID（用于查询参数）
     */
    private String corpId;
    /**
     * 父单位名称
     */
    private String parentName;

    /**
     * 煤矿类型
     */
    private String mineTypeName;
    /**
     * 子单位集合
     */
    private List<CorpVOExt> children;
    /**
     * 当前用户corpId
     */
    private String currentCorpId;

}