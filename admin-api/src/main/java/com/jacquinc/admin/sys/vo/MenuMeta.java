package com.jacquinc.admin.sys.vo;

import com.jiujie.framework.base.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjq
 * created on  2019/10/30 22:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuMeta extends BaseVO {
    private String title;
    private String icon;
}
