package com.jacquinc.admin.application.utils;

import com.google.common.collect.Lists;
import com.jiujie.framework.base.utils.ReflectionUtils;
import com.jacquinc.admin.application.vo.TreeSelectNode;

import java.util.List;
import java.util.Optional;

/**
 * @author cjq
 * created on  2021/03/05
 */
@SuppressWarnings("unchecked")
public class TreeUtils {

    public static <Node> List<TreeSelectNode> getTreeForSelect(List<Node> list) {
        List<TreeSelectNode> treeSelectNodes = Lists.newArrayList();
        for (Node node : list) {
            TreeSelectNode tsn = transfer(node);
            treeSelectNodes.add(tsn);
        }
        return treeSelectNodes;
    }

    private static <Node> TreeSelectNode transfer(Node node) {
        TreeSelectNode tsn = new TreeSelectNode();
        tsn.setId(Optional.ofNullable(ReflectionUtils.getFieldValue(node, "id")).orElse("").toString());
        tsn.setValue(tsn.getId());
        tsn.setLabel(Optional.ofNullable(ReflectionUtils.getFieldValue(node, "name")).orElse("").toString());
        List<Node> children = (List<Node>) ReflectionUtils.getFieldValue(node, "children");
        if (children != null && !children.isEmpty()) {
            List<TreeSelectNode> childrenTsn = Lists.newArrayList();
            for (Node child : children) {
                childrenTsn.add(transfer(child));
            }
            tsn.setChildren(childrenTsn);
        }
        return tsn;
    }

}
