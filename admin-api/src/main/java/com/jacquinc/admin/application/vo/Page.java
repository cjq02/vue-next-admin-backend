package com.jacquinc.admin.application.vo;

import com.jacquinc.admin.utils.StringUtils;

/**
 * 翻页对象
 *
 * @author Administrator
 */
public class Page<T, X> extends com.jiujie.framework.mybatis.dao.pojo.Page {

    private X condition;

    private String sortBy;

    private String sortType;

    public X getCondition() {
        return condition;
    }

    public void setCondition(X condition) {
        this.condition = condition;
    }

    public String getSortBy() {
        return StringUtils.camelCaseToSnakeCase(StringUtils.ifEmpty(this.sortBy, ""));
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
