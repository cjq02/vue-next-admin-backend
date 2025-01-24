package com.jacquinc.admin.application.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 配合vue-treeselect使用的节点对象
 *
 * @author cjq
 * created on  2021/03/10
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeSelectNode {
    private String id;
    private String value;
    private String label;
    private List<TreeSelectNode> children;
}
