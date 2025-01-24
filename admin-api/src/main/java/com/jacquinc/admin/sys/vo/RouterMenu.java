package com.jacquinc.admin.sys.vo;

import com.google.common.collect.Lists;
import com.jiujie.framework.base.vo.BaseVO;
import lombok.Data;

import java.util.List;

/**
 * @author cjq
 * created on  2021/03/05
 */
@Data
public class RouterMenu extends BaseVO {
    private String id;
    private String parentId;
    private String path;
    private String component;
    private String name;
    private Integer priority;
    private Boolean hidden = false;
    private MenuMeta meta;
    private List<RouterMenu> children = Lists.newArrayList();
}
