package com.jacquinc.admin.sys.entity;

import com.jiujie.framework.mybatis.entity.BaseEntity;
import com.jiujie.framework.mybatis.entity.condition.BaseEntityCondition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileEntityCondition extends BaseEntityCondition implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FileEntityCondition() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    @Override
    public Class<? extends BaseEntity> getEntityClass() {
        return FileEntity.class;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNull() {
            addCriterion("file_type is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNotNull() {
            addCriterion("file_type is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeEqualTo(String value) {
            addCriterion("file_type =", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotEqualTo(String value) {
            addCriterion("file_type <>", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThan(String value) {
            addCriterion("file_type >", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThanOrEqualTo(String value) {
            addCriterion("file_type >=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThan(String value) {
            addCriterion("file_type <", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThanOrEqualTo(String value) {
            addCriterion("file_type <=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLike(String value) {
            addCriterion("file_type like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotLike(String value) {
            addCriterion("file_type not like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeIn(List<String> values) {
            addCriterion("file_type in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotIn(List<String> values) {
            addCriterion("file_type not in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeBetween(String value1, String value2) {
            addCriterion("file_type between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotBetween(String value1, String value2) {
            addCriterion("file_type not between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNull() {
            addCriterion("file_url is null");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNotNull() {
            addCriterion("file_url is not null");
            return (Criteria) this;
        }

        public Criteria andFileUrlEqualTo(String value) {
            addCriterion("file_url =", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotEqualTo(String value) {
            addCriterion("file_url <>", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThan(String value) {
            addCriterion("file_url >", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("file_url >=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThan(String value) {
            addCriterion("file_url <", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThanOrEqualTo(String value) {
            addCriterion("file_url <=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLike(String value) {
            addCriterion("file_url like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotLike(String value) {
            addCriterion("file_url not like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlIn(List<String> values) {
            addCriterion("file_url in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotIn(List<String> values) {
            addCriterion("file_url not in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlBetween(String value1, String value2) {
            addCriterion("file_url between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotBetween(String value1, String value2) {
            addCriterion("file_url not between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("file_size is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("file_size is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(Long value) {
            addCriterion("file_size =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(Long value) {
            addCriterion("file_size <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(Long value) {
            addCriterion("file_size >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("file_size >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(Long value) {
            addCriterion("file_size <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(Long value) {
            addCriterion("file_size <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<Long> values) {
            addCriterion("file_size in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<Long> values) {
            addCriterion("file_size not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(Long value1, Long value2) {
            addCriterion("file_size between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(Long value1, Long value2) {
            addCriterion("file_size not between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andOssKeyIsNull() {
            addCriterion("oss_key is null");
            return (Criteria) this;
        }

        public Criteria andOssKeyIsNotNull() {
            addCriterion("oss_key is not null");
            return (Criteria) this;
        }

        public Criteria andOssKeyEqualTo(String value) {
            addCriterion("oss_key =", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyNotEqualTo(String value) {
            addCriterion("oss_key <>", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyGreaterThan(String value) {
            addCriterion("oss_key >", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyGreaterThanOrEqualTo(String value) {
            addCriterion("oss_key >=", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyLessThan(String value) {
            addCriterion("oss_key <", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyLessThanOrEqualTo(String value) {
            addCriterion("oss_key <=", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyLike(String value) {
            addCriterion("oss_key like", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyNotLike(String value) {
            addCriterion("oss_key not like", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyIn(List<String> values) {
            addCriterion("oss_key in", values, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyNotIn(List<String> values) {
            addCriterion("oss_key not in", values, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyBetween(String value1, String value2) {
            addCriterion("oss_key between", value1, value2, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyNotBetween(String value1, String value2) {
            addCriterion("oss_key not between", value1, value2, "ossKey");
            return (Criteria) this;
        }

        public Criteria andCreateTsIsNull() {
            addCriterion("create_ts is null");
            return (Criteria) this;
        }

        public Criteria andCreateTsIsNotNull() {
            addCriterion("create_ts is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTsEqualTo(Date value) {
            addCriterion("create_ts =", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsNotEqualTo(Date value) {
            addCriterion("create_ts <>", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsGreaterThan(Date value) {
            addCriterion("create_ts >", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsGreaterThanOrEqualTo(Date value) {
            addCriterion("create_ts >=", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsLessThan(Date value) {
            addCriterion("create_ts <", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsLessThanOrEqualTo(Date value) {
            addCriterion("create_ts <=", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsIn(List<Date> values) {
            addCriterion("create_ts in", values, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsNotIn(List<Date> values) {
            addCriterion("create_ts not in", values, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsBetween(Date value1, Date value2) {
            addCriterion("create_ts between", value1, value2, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsNotBetween(Date value1, Date value2) {
            addCriterion("create_ts not between", value1, value2, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(String value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(String value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(String value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(String value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(String value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLike(String value) {
            addCriterion("create_user_id like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotLike(String value) {
            addCriterion("create_user_id not like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<String> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<String> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(String value1, String value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(String value1, String value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTsIsNull() {
            addCriterion("update_ts is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTsIsNotNull() {
            addCriterion("update_ts is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTsEqualTo(Date value) {
            addCriterion("update_ts =", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsNotEqualTo(Date value) {
            addCriterion("update_ts <>", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsGreaterThan(Date value) {
            addCriterion("update_ts >", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsGreaterThanOrEqualTo(Date value) {
            addCriterion("update_ts >=", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsLessThan(Date value) {
            addCriterion("update_ts <", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsLessThanOrEqualTo(Date value) {
            addCriterion("update_ts <=", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsIn(List<Date> values) {
            addCriterion("update_ts in", values, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsNotIn(List<Date> values) {
            addCriterion("update_ts not in", values, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsBetween(Date value1, Date value2) {
            addCriterion("update_ts between", value1, value2, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsNotBetween(Date value1, Date value2) {
            addCriterion("update_ts not between", value1, value2, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(String value) {
            addCriterion("update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(String value) {
            addCriterion("update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(String value) {
            addCriterion("update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(String value) {
            addCriterion("update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(String value) {
            addCriterion("update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLike(String value) {
            addCriterion("update_user_id like", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotLike(String value) {
            addCriterion("update_user_id not like", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<String> values) {
            addCriterion("update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<String> values) {
            addCriterion("update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(String value1, String value2) {
            addCriterion("update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(String value1, String value2) {
            addCriterion("update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andHashCodeIsNull() {
            addCriterion("hash_code is null");
            return (Criteria) this;
        }

        public Criteria andHashCodeIsNotNull() {
            addCriterion("hash_code is not null");
            return (Criteria) this;
        }

        public Criteria andHashCodeEqualTo(String value) {
            addCriterion("hash_code =", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeNotEqualTo(String value) {
            addCriterion("hash_code <>", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeGreaterThan(String value) {
            addCriterion("hash_code >", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeGreaterThanOrEqualTo(String value) {
            addCriterion("hash_code >=", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeLessThan(String value) {
            addCriterion("hash_code <", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeLessThanOrEqualTo(String value) {
            addCriterion("hash_code <=", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeLike(String value) {
            addCriterion("hash_code like", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeNotLike(String value) {
            addCriterion("hash_code not like", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeIn(List<String> values) {
            addCriterion("hash_code in", values, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeNotIn(List<String> values) {
            addCriterion("hash_code not in", values, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeBetween(String value1, String value2) {
            addCriterion("hash_code between", value1, value2, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeNotBetween(String value1, String value2) {
            addCriterion("hash_code not between", value1, value2, "hashCode");
            return (Criteria) this;
        }

        public Criteria andBizIdIsNull() {
            addCriterion("biz_id is null");
            return (Criteria) this;
        }

        public Criteria andBizIdIsNotNull() {
            addCriterion("biz_id is not null");
            return (Criteria) this;
        }

        public Criteria andBizIdEqualTo(String value) {
            addCriterion("biz_id =", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotEqualTo(String value) {
            addCriterion("biz_id <>", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThan(String value) {
            addCriterion("biz_id >", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThanOrEqualTo(String value) {
            addCriterion("biz_id >=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThan(String value) {
            addCriterion("biz_id <", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThanOrEqualTo(String value) {
            addCriterion("biz_id <=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLike(String value) {
            addCriterion("biz_id like", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotLike(String value) {
            addCriterion("biz_id not like", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdIn(List<String> values) {
            addCriterion("biz_id in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotIn(List<String> values) {
            addCriterion("biz_id not in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdBetween(String value1, String value2) {
            addCriterion("biz_id between", value1, value2, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotBetween(String value1, String value2) {
            addCriterion("biz_id not between", value1, value2, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizTypeIsNull() {
            addCriterion("biz_type is null");
            return (Criteria) this;
        }

        public Criteria andBizTypeIsNotNull() {
            addCriterion("biz_type is not null");
            return (Criteria) this;
        }

        public Criteria andBizTypeEqualTo(String value) {
            addCriterion("biz_type =", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotEqualTo(String value) {
            addCriterion("biz_type <>", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeGreaterThan(String value) {
            addCriterion("biz_type >", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeGreaterThanOrEqualTo(String value) {
            addCriterion("biz_type >=", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLessThan(String value) {
            addCriterion("biz_type <", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLessThanOrEqualTo(String value) {
            addCriterion("biz_type <=", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLike(String value) {
            addCriterion("biz_type like", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotLike(String value) {
            addCriterion("biz_type not like", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeIn(List<String> values) {
            addCriterion("biz_type in", values, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotIn(List<String> values) {
            addCriterion("biz_type not in", values, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeBetween(String value1, String value2) {
            addCriterion("biz_type between", value1, value2, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotBetween(String value1, String value2) {
            addCriterion("biz_type not between", value1, value2, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeIsNull() {
            addCriterion("biz_detail_type is null");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeIsNotNull() {
            addCriterion("biz_detail_type is not null");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeEqualTo(String value) {
            addCriterion("biz_detail_type =", value, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeNotEqualTo(String value) {
            addCriterion("biz_detail_type <>", value, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeGreaterThan(String value) {
            addCriterion("biz_detail_type >", value, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeGreaterThanOrEqualTo(String value) {
            addCriterion("biz_detail_type >=", value, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeLessThan(String value) {
            addCriterion("biz_detail_type <", value, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeLessThanOrEqualTo(String value) {
            addCriterion("biz_detail_type <=", value, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeLike(String value) {
            addCriterion("biz_detail_type like", value, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeNotLike(String value) {
            addCriterion("biz_detail_type not like", value, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeIn(List<String> values) {
            addCriterion("biz_detail_type in", values, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeNotIn(List<String> values) {
            addCriterion("biz_detail_type not in", values, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeBetween(String value1, String value2) {
            addCriterion("biz_detail_type between", value1, value2, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeNotBetween(String value1, String value2) {
            addCriterion("biz_detail_type not between", value1, value2, "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlIsNull() {
            addCriterion("thumbnail_file_url is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlIsNotNull() {
            addCriterion("thumbnail_file_url is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlEqualTo(String value) {
            addCriterion("thumbnail_file_url =", value, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlNotEqualTo(String value) {
            addCriterion("thumbnail_file_url <>", value, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlGreaterThan(String value) {
            addCriterion("thumbnail_file_url >", value, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("thumbnail_file_url >=", value, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlLessThan(String value) {
            addCriterion("thumbnail_file_url <", value, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlLessThanOrEqualTo(String value) {
            addCriterion("thumbnail_file_url <=", value, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlLike(String value) {
            addCriterion("thumbnail_file_url like", value, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlNotLike(String value) {
            addCriterion("thumbnail_file_url not like", value, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlIn(List<String> values) {
            addCriterion("thumbnail_file_url in", values, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlNotIn(List<String> values) {
            addCriterion("thumbnail_file_url not in", values, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlBetween(String value1, String value2) {
            addCriterion("thumbnail_file_url between", value1, value2, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlNotBetween(String value1, String value2) {
            addCriterion("thumbnail_file_url not between", value1, value2, "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeIsNull() {
            addCriterion("thumbnail_file_size is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeIsNotNull() {
            addCriterion("thumbnail_file_size is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeEqualTo(Long value) {
            addCriterion("thumbnail_file_size =", value, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeNotEqualTo(Long value) {
            addCriterion("thumbnail_file_size <>", value, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeGreaterThan(Long value) {
            addCriterion("thumbnail_file_size >", value, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("thumbnail_file_size >=", value, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeLessThan(Long value) {
            addCriterion("thumbnail_file_size <", value, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeLessThanOrEqualTo(Long value) {
            addCriterion("thumbnail_file_size <=", value, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeIn(List<Long> values) {
            addCriterion("thumbnail_file_size in", values, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeNotIn(List<Long> values) {
            addCriterion("thumbnail_file_size not in", values, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeBetween(Long value1, Long value2) {
            addCriterion("thumbnail_file_size between", value1, value2, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileSizeNotBetween(Long value1, Long value2) {
            addCriterion("thumbnail_file_size not between", value1, value2, "thumbnailFileSize");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeIsNull() {
            addCriterion("thumbnail_hash_code is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeIsNotNull() {
            addCriterion("thumbnail_hash_code is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeEqualTo(String value) {
            addCriterion("thumbnail_hash_code =", value, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeNotEqualTo(String value) {
            addCriterion("thumbnail_hash_code <>", value, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeGreaterThan(String value) {
            addCriterion("thumbnail_hash_code >", value, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeGreaterThanOrEqualTo(String value) {
            addCriterion("thumbnail_hash_code >=", value, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeLessThan(String value) {
            addCriterion("thumbnail_hash_code <", value, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeLessThanOrEqualTo(String value) {
            addCriterion("thumbnail_hash_code <=", value, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeLike(String value) {
            addCriterion("thumbnail_hash_code like", value, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeNotLike(String value) {
            addCriterion("thumbnail_hash_code not like", value, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeIn(List<String> values) {
            addCriterion("thumbnail_hash_code in", values, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeNotIn(List<String> values) {
            addCriterion("thumbnail_hash_code not in", values, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeBetween(String value1, String value2) {
            addCriterion("thumbnail_hash_code between", value1, value2, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeNotBetween(String value1, String value2) {
            addCriterion("thumbnail_hash_code not between", value1, value2, "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andParameter1IsNull() {
            addCriterion("parameter1 is null");
            return (Criteria) this;
        }

        public Criteria andParameter1IsNotNull() {
            addCriterion("parameter1 is not null");
            return (Criteria) this;
        }

        public Criteria andParameter1EqualTo(String value) {
            addCriterion("parameter1 =", value, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1NotEqualTo(String value) {
            addCriterion("parameter1 <>", value, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1GreaterThan(String value) {
            addCriterion("parameter1 >", value, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1GreaterThanOrEqualTo(String value) {
            addCriterion("parameter1 >=", value, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1LessThan(String value) {
            addCriterion("parameter1 <", value, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1LessThanOrEqualTo(String value) {
            addCriterion("parameter1 <=", value, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1Like(String value) {
            addCriterion("parameter1 like", value, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1NotLike(String value) {
            addCriterion("parameter1 not like", value, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1In(List<String> values) {
            addCriterion("parameter1 in", values, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1NotIn(List<String> values) {
            addCriterion("parameter1 not in", values, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1Between(String value1, String value2) {
            addCriterion("parameter1 between", value1, value2, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter1NotBetween(String value1, String value2) {
            addCriterion("parameter1 not between", value1, value2, "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter2IsNull() {
            addCriterion("parameter2 is null");
            return (Criteria) this;
        }

        public Criteria andParameter2IsNotNull() {
            addCriterion("parameter2 is not null");
            return (Criteria) this;
        }

        public Criteria andParameter2EqualTo(String value) {
            addCriterion("parameter2 =", value, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2NotEqualTo(String value) {
            addCriterion("parameter2 <>", value, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2GreaterThan(String value) {
            addCriterion("parameter2 >", value, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2GreaterThanOrEqualTo(String value) {
            addCriterion("parameter2 >=", value, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2LessThan(String value) {
            addCriterion("parameter2 <", value, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2LessThanOrEqualTo(String value) {
            addCriterion("parameter2 <=", value, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2Like(String value) {
            addCriterion("parameter2 like", value, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2NotLike(String value) {
            addCriterion("parameter2 not like", value, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2In(List<String> values) {
            addCriterion("parameter2 in", values, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2NotIn(List<String> values) {
            addCriterion("parameter2 not in", values, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2Between(String value1, String value2) {
            addCriterion("parameter2 between", value1, value2, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter2NotBetween(String value1, String value2) {
            addCriterion("parameter2 not between", value1, value2, "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter3IsNull() {
            addCriterion("parameter3 is null");
            return (Criteria) this;
        }

        public Criteria andParameter3IsNotNull() {
            addCriterion("parameter3 is not null");
            return (Criteria) this;
        }

        public Criteria andParameter3EqualTo(String value) {
            addCriterion("parameter3 =", value, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3NotEqualTo(String value) {
            addCriterion("parameter3 <>", value, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3GreaterThan(String value) {
            addCriterion("parameter3 >", value, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3GreaterThanOrEqualTo(String value) {
            addCriterion("parameter3 >=", value, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3LessThan(String value) {
            addCriterion("parameter3 <", value, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3LessThanOrEqualTo(String value) {
            addCriterion("parameter3 <=", value, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3Like(String value) {
            addCriterion("parameter3 like", value, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3NotLike(String value) {
            addCriterion("parameter3 not like", value, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3In(List<String> values) {
            addCriterion("parameter3 in", values, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3NotIn(List<String> values) {
            addCriterion("parameter3 not in", values, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3Between(String value1, String value2) {
            addCriterion("parameter3 between", value1, value2, "parameter3");
            return (Criteria) this;
        }

        public Criteria andParameter3NotBetween(String value1, String value2) {
            addCriterion("parameter3 not between", value1, value2, "parameter3");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNull() {
            addCriterion("seq_no is null");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNotNull() {
            addCriterion("seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNoEqualTo(Integer value) {
            addCriterion("seq_no =", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotEqualTo(Integer value) {
            addCriterion("seq_no <>", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThan(Integer value) {
            addCriterion("seq_no >", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq_no >=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThan(Integer value) {
            addCriterion("seq_no <", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThanOrEqualTo(Integer value) {
            addCriterion("seq_no <=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoIn(List<Integer> values) {
            addCriterion("seq_no in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotIn(List<Integer> values) {
            addCriterion("seq_no not in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoBetween(Integer value1, Integer value2) {
            addCriterion("seq_no between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotBetween(Integer value1, Integer value2) {
            addCriterion("seq_no not between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andFileNameLikeInsensitive(String value) {
            addCriterion("upper(file_name) like", value.toUpperCase(), "fileName");
            return (Criteria) this;
        }

        public Criteria andFileTypeLikeInsensitive(String value) {
            addCriterion("upper(file_type) like", value.toUpperCase(), "fileType");
            return (Criteria) this;
        }

        public Criteria andFileUrlLikeInsensitive(String value) {
            addCriterion("upper(file_url) like", value.toUpperCase(), "fileUrl");
            return (Criteria) this;
        }

        public Criteria andOssKeyLikeInsensitive(String value) {
            addCriterion("upper(oss_key) like", value.toUpperCase(), "ossKey");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLikeInsensitive(String value) {
            addCriterion("upper(create_user_id) like", value.toUpperCase(), "createUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLikeInsensitive(String value) {
            addCriterion("upper(update_user_id) like", value.toUpperCase(), "updateUserId");
            return (Criteria) this;
        }

        public Criteria andHashCodeLikeInsensitive(String value) {
            addCriterion("upper(hash_code) like", value.toUpperCase(), "hashCode");
            return (Criteria) this;
        }

        public Criteria andBizIdLikeInsensitive(String value) {
            addCriterion("upper(biz_id) like", value.toUpperCase(), "bizId");
            return (Criteria) this;
        }

        public Criteria andBizTypeLikeInsensitive(String value) {
            addCriterion("upper(biz_type) like", value.toUpperCase(), "bizType");
            return (Criteria) this;
        }

        public Criteria andBizDetailTypeLikeInsensitive(String value) {
            addCriterion("upper(biz_detail_type) like", value.toUpperCase(), "bizDetailType");
            return (Criteria) this;
        }

        public Criteria andThumbnailFileUrlLikeInsensitive(String value) {
            addCriterion("upper(thumbnail_file_url) like", value.toUpperCase(), "thumbnailFileUrl");
            return (Criteria) this;
        }

        public Criteria andThumbnailHashCodeLikeInsensitive(String value) {
            addCriterion("upper(thumbnail_hash_code) like", value.toUpperCase(), "thumbnailHashCode");
            return (Criteria) this;
        }

        public Criteria andParameter1LikeInsensitive(String value) {
            addCriterion("upper(parameter1) like", value.toUpperCase(), "parameter1");
            return (Criteria) this;
        }

        public Criteria andParameter2LikeInsensitive(String value) {
            addCriterion("upper(parameter2) like", value.toUpperCase(), "parameter2");
            return (Criteria) this;
        }

        public Criteria andParameter3LikeInsensitive(String value) {
            addCriterion("upper(parameter3) like", value.toUpperCase(), "parameter3");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andIdLikeIgnoreCase(String value) {
            addCriterion("upper(id) like ", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andFileNameLikeIgnoreCase(String value) {
            addCriterion("upper(file_name) like ", value.toUpperCase(), "fileName");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andFileTypeLikeIgnoreCase(String value) {
            addCriterion("upper(file_type) like ", value.toUpperCase(), "fileType");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andFileUrlLikeIgnoreCase(String value) {
            addCriterion("upper(file_url) like ", value.toUpperCase(), "fileUrl");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andOssKeyLikeIgnoreCase(String value) {
            addCriterion("upper(oss_key) like ", value.toUpperCase(), "ossKey");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andCreateUserIdLikeIgnoreCase(String value) {
            addCriterion("upper(create_user_id) like ", value.toUpperCase(), "createUserId");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andUpdateUserIdLikeIgnoreCase(String value) {
            addCriterion("upper(update_user_id) like ", value.toUpperCase(), "updateUserId");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andHashCodeLikeIgnoreCase(String value) {
            addCriterion("upper(hash_code) like ", value.toUpperCase(), "hashCode");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andBizIdLikeIgnoreCase(String value) {
            addCriterion("upper(biz_id) like ", value.toUpperCase(), "bizId");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andBizTypeLikeIgnoreCase(String value) {
            addCriterion("upper(biz_type) like ", value.toUpperCase(), "bizType");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andBizDetailTypeLikeIgnoreCase(String value) {
            addCriterion("upper(biz_detail_type) like ", value.toUpperCase(), "bizDetailType");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andThumbnailFileUrlLikeIgnoreCase(String value) {
            addCriterion("upper(thumbnail_file_url) like ", value.toUpperCase(), "thumbnailFileUrl");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andThumbnailHashCodeLikeIgnoreCase(String value) {
            addCriterion("upper(thumbnail_hash_code) like ", value.toUpperCase(), "thumbnailHashCode");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andParameter1LikeIgnoreCase(String value) {
            addCriterion("upper(parameter1) like ", value.toUpperCase(), "parameter1");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andParameter2LikeIgnoreCase(String value) {
            addCriterion("upper(parameter2) like ", value.toUpperCase(), "parameter2");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andParameter3LikeIgnoreCase(String value) {
            addCriterion("upper(parameter3) like ", value.toUpperCase(), "parameter3");
            return (Criteria) this;
        }

        /**
         *(扩展Mybatis原生like的不足)
         *忽略字段大小写的模糊查询
         *Java编码如下：
         *  criteria.andNameLikeIgnoreCase("%Abc%"); 前后模糊,A字母大写
         *  criteria.andName2LikeIgnoreCase("%aBc"); 前模糊,B字母大写
         *  criteria.andName3LikeIgnoreCase("abC%"); 后模糊,C字母大写
         *执行时SQL如下:
         *  where name like '%abc%' or name2 like '%abc' or name3 like 'abc%'
         */
        public Criteria andRemarkLikeIgnoreCase(String value) {
            addCriterion("upper(remark) like ", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}