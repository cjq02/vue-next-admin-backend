package com.jacquinc.admin.sys.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jacquinc.admin.application.annotation.EnumCodeInfo;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.vo.EnumVO;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.entity.CodeEntity;
import com.jacquinc.admin.sys.entity.CodeEntityCondition;
import com.jacquinc.admin.sys.enumerate.CodeModifyStatusEnum;
import com.jacquinc.admin.sys.vo.CodeVO;
import com.jacquinc.admin.sys.vo.CodeVOExt;
import com.jacquinc.admin.utils.StringUtils;
import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.base.utils.DateUtils;
import com.jiujie.framework.base.vo.BaseVO;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 码表 接口实现类
 *
 * @author cjq
 * created on  2020/08/18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CodeService extends BaseServiceImpl implements ICodeService {

    private static final String MAPPER_NAMESPACE = "com.jacquinc.admin.sys.sqlmapper.CodeMapper";

    private static final String TYPE_CODE_SUFFIX_DISTINCT = ":DISTINCT";

    private static final String ENUM_CLASS_LIST_CACHE = "enumCache";

    @Override
    public Page<CodeVOExt, CodeVOExt> getCodePage(Page<CodeVOExt, CodeVOExt> page) {
        Map<String, Object> params = Maps.newHashMap();
        CodeVOExt condition = page.getCondition();
        params.put("page", page);
        params.put("condition", condition);
        List<CodeVO> list = this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findCodeList", params);
        int count = this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + ".countCode", params);

        if (list.size() == 0) {
            List<EnumVO> enumList = getEnumList(condition.getTypeCode());
            list = enumList.stream().map(vo -> {
                CodeVO codeVO = BeanUtils.copyToNewBean(vo, CodeVO.class);
                codeVO.setTypeCode(condition.getTypeCode());
                codeVO.setTypeName(getEnumCodeInfo(condition.getTypeCode()).typeName());
                codeVO.setConfigCode(vo.getCode());
                codeVO.setConfigName(vo.getName());
                return codeVO;
            }).collect(Collectors.toList());
        }
        page.setTotalRecord(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public Page<CodeVOExt, CodeVOExt> getEnumPage(Page<CodeVOExt, CodeVOExt> page) throws BusinessException {
        CodeVOExt condition = page.getCondition();
        List<EnumVO> list = getEnumList(condition.getTypeCode());
        page.setTotalRecord(list.size());
        page.setRecords(list);
        return page;
    }

    @Override
    public Page<CodeVOExt, CodeVOExt> getCodeGroupPage(Page<CodeVOExt, CodeVOExt> page) {
        Map<String, Object> params = Maps.newHashMap();
        CodeVOExt condition = page.getCondition();
        params.put("page", page);
        params.put("condition", condition);
        List<CodeVOExt> groupList = this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findCodeGroupList", params);
        List<CodeVOExt> codeList = findCodeList();
        List<ModifyCode> modifyCodeList = getModifiedCodeList(codeList);

        List<CodeVOExt> addList = modifyCodeList.stream().filter(modifyCode -> modifyCode.getModifyStatus().equals(CodeModifyStatusEnum.ADD))
                .map(vo -> {
                    CodeVOExt newCode = new CodeVOExt();
                    newCode.setId(vo.getTypeCode());
                    newCode.setModifyStatus(vo.getModifyStatus().getCode());
                    newCode.setTypeCode(vo.getTypeCode());
                    newCode.setTypeName(vo.getTypeName());
                    return newCode;
                })
                .collect(Collectors.toList());

        groupList.forEach(vo -> {
            vo.setIsEnum(isEnum(modifyCodeList, vo));
            vo.setModifyStatus(getMatchStatus(modifyCodeList, vo));
        });
        groupList.addAll(addList);

        Map<String, List<CodeVO>> codeGroupMap = getCodeGroupMap(codeList);
        groupList.forEach(vo -> vo.setCodeList(codeGroupMap.get(vo.getTypeCode())));

        groupList = groupList.stream().sorted(Comparator.comparing(CodeVOExt::getModifyStatus)).collect(Collectors.toList());

        page.setTotalRecord(groupList.size());
        page.setRecords(groupList);
        return page;
    }

    private String isEnum(List<ModifyCode> modifyCodeList, CodeVOExt vo) {
        return modifyCodeList.stream().anyMatch(modifyCode -> modifyCode.getTypeCode().equals(vo.getTypeCode())) ? Constants.YES : Constants.NO;
    }

    private String getMatchStatus(List<ModifyCode> modifyCodeList, CodeVOExt codeVO) {
        ModifyCode modifyCode = modifyCodeList.stream().filter(vo -> vo.getTypeCode().equals(codeVO.getTypeCode())).findFirst()
                .orElse(new ModifyCode(CodeModifyStatusEnum.ENUM_NOT_EXISTS));
        Date yesterday = DateUtils.getYesterday();
        Date now = DateUtils.getCurrentDate();
        boolean isChangeRecently = DateUtils.inDateRange(yesterday, now, codeVO.getCreateTs()) || DateUtils.inDateRange(yesterday, now, codeVO.getUpdateTs());
        boolean isChanging = modifyCode.getModifyStatus().equals(CodeModifyStatusEnum.UPDATE) || modifyCode.getModifyStatus().equals(CodeModifyStatusEnum.ADD);
        if (!isChanging && isChangeRecently) {
            modifyCode.setModifyStatus(CodeModifyStatusEnum.GENERATED);
        }
        return modifyCode.getModifyStatus().getCode();
    }

    @Override
    public List<CodeVOExt> findCodeList() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("condition", new CodeVOExt());
        return this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findCodeList", params);
    }

    @Override
    public List<CodeVOExt> findByTypeCode(String typeCode) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("condition", new CodeVOExt());
        params.put("typeCode", typeCode);
        return this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findCodeList", params);
    }

    @Override
    public CodeVO getCodeByParams(String typeCode, String configName) {
        CodeEntityCondition condition = new CodeEntityCondition();
        condition.createCriteria().andTypeCodeEqualTo(typeCode).andConfigNameEqualTo(configName);
        CodeEntity entity = this.selectOneByCondition(condition);
        return BeanUtils.copyToNewBean(entity, CodeVO.class);

    }

    @Override
    public String getConfigCodeByParams(String typeCode, String configName) {
        CodeEntityCondition condition = new CodeEntityCondition();
        condition.createCriteria().andTypeCodeEqualTo(typeCode).andConfigNameEqualTo(configName);
        CodeEntity entity = this.selectOneByCondition(condition);
        if (entity != null) {
            return entity.getConfigCode();
        }
        return "";
    }

    @Override
    public String getConfigNameByParams(String typeCode, String configCode) {
        CodeEntityCondition condition = new CodeEntityCondition();
        condition.createCriteria().andTypeCodeEqualTo(typeCode).andConfigCodeEqualTo(configCode);
        CodeEntity entity = this.selectOneByCondition(condition);
        if (entity != null) {
            return entity.getConfigName();
        }
        return "";
    }

    @Override
    public void saveCodeList(List<CodeVOExt> list, String currentUserId) {
        List<CodeVOExt> addDataList = list.stream().filter(item -> item.getRowState().equals(BaseVO.RowStateEnum.ADDED.getValue())).collect(Collectors.toList());
        List<CodeVOExt> delDataList = list.stream().filter(item -> item.getRowState().equals(BaseVO.RowStateEnum.DELETED.getValue())).collect(Collectors.toList());
        List<CodeVOExt> updateDataList = Lists.newArrayList(list);
        updateDataList.removeAll(addDataList);
        updateDataList.removeAll(delDataList);
        addDataList.forEach(vo -> {
            vo.setCreateUserId(currentUserId);
            vo.setCreateTs(DateUtils.getCurrentDate());
        });
        updateDataList.forEach(vo -> {
            vo.setUpdateUserId(currentUserId);
            vo.setUpdateTs(DateUtils.getCurrentDate());
        });
        // 新增
        this.insertList(BeanUtils.copyToNewList(addDataList, CodeEntity.class));
        // 修改
        this.update(BeanUtils.copyToNewList(updateDataList, CodeEntity.class));
        // 删除
        this.delete(BeanUtils.copyToNewList(delDataList, CodeEntity.class));
    }

    @Override
    public Class<?> getEnumClass(String typeCode) {
        for (Class<?> enumerate : getEnumClassList()) {
            EnumCodeInfo enumCodeInfo = enumerate.getAnnotation(EnumCodeInfo.class);
            if (typeCode.equals(getTypeCode(enumerate, enumCodeInfo))) {
                return enumerate;
            }
        }

        return null;
    }

    @Override
    public List<String> getEnumPackageList() {
        Set<String> packageList = Sets.newHashSet();
        for (Class<?> enumerate : getEnumClassList()) {
            packageList.add(enumerate.getName().substring(0, enumerate.getName().lastIndexOf(".")));
        }
        return Lists.newArrayList(packageList);
    }

    @Override
    public Set<Class<?>> getEnumClassList() {
        Set<Class<?>> enumClassList;
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addUrls(ClasspathHelper.forPackage(Constants.PACKAGE_PREFIX));
        builder.filterInputsBy(new FilterBuilder().include(".*enumerate.*"));
        enumClassList = new Reflections(builder).getTypesAnnotatedWith(EnumCodeInfo.class);
        return enumClassList;
    }

    @Override
    public List<EnumVO> getEnumList(String typeCode) {
        for (Class<?> enumerate : getEnumClassList()) {
            EnumCodeInfo enumCodeInfo = enumerate.getAnnotation(EnumCodeInfo.class);
            if (typeCode.equals(getTypeCode(enumerate, enumCodeInfo))) {
                return getEnumList(enumerate);
            }
        }

        throw new BusinessException("TYPE_CODE: " + typeCode + " 对应的枚举不存在！");
    }

    @Override
    public List<EnumVO> getEnumList(Class<?> enumerate) {
        return Lists.newArrayList(enumerate.getEnumConstants()).stream().map(item -> {
            EnumVO enumVO = new EnumVO();
            try {
                enumVO.setKey((String) enumerate.getMethod("toString").invoke(item));
                enumVO.setCode((String) enumerate.getMethod("getCode").invoke(item));
                enumVO.setName((String) enumerate.getMethod("getName").invoke(item));
                try {
                    enumVO.setRemark((String) enumerate.getMethod("getRemark").invoke(item));
                } catch (NoSuchMethodException ignored) {
                }
                try {
                    enumVO.setParentCode((String) enumerate.getMethod("getParentCode").invoke(item));
                } catch (NoSuchMethodException ignored) {
                }
            } catch (Exception e) {
                logger.error("获取枚举属性失败！枚举：{}", enumerate.getName());
            }
            return enumVO;
        }).collect(Collectors.toList());
    }

    private EnumCodeInfo getEnumCodeInfo(String typeCode) {
        for (Class<?> enumerate : getEnumClassList()) {
            EnumCodeInfo enumCodeInfo = enumerate.getAnnotation(EnumCodeInfo.class);
            if (typeCode.equals(getTypeCode(enumerate, enumCodeInfo))) {
                return enumCodeInfo;
            }
        }

        throw new BusinessException("TYPE_CODE: " + typeCode + " 对应的枚举不存在！");
    }


    @Override
    public Boolean generateCode() {
        List<CodeVOExt> codeList = findCodeList();
        List<ModifyCode> modifyList = getModifiedCodeList(codeList);
        return doGenerate(modifyList);
    }

    private Boolean doGenerate(List<ModifyCode> modifyList) {
        List<String> addList = new ArrayList<>();
        List<String> updateList = new ArrayList<>();
        modifyList.forEach(modifyCode -> {
            switch (modifyCode.getModifyStatus()) {
                case ADD:
                    insertList(modifyCode);
                    addList.add(modifyCode.getTypeCode());
                    break;
                case UPDATE:
                    deleteThenInsertList(modifyCode);
                    updateList.add(modifyCode.getTypeCode());
                    break;
                default:
            }
        });

        /*String addStr = StringUtils.join(addList, "，");
        String updateStr = StringUtils.join(updateList, "，");*/
        /*return String.format("新增码表：[%s]，变更码表：[%s]", addStr, updateStr);*/
        return addList.size() > 0 && updateList.size() > 0;
    }

    @Override
    public Boolean generateCode(CodeVOExt params) throws BusinessException {
        CodeModifyStatusEnum modifyStatus = CodeModifyStatusEnum.getByCode(params.getModifyStatus());
        List<EnumVO> enumList = getEnumList(params.getTypeCode());
        EnumCodeInfo enumCodeInfo = getEnumCodeInfo(params.getTypeCode());
        ModifyCode modifyCode = new ModifyCode(modifyStatus, params.getTypeCode(), enumCodeInfo.typeName(), enumList, DateUtils.getCurrentDate());
        return doGenerate(Lists.newArrayList(modifyCode));
    }

    private List<ModifyCode> getModifiedCodeList(List<CodeVOExt> codeVOList) {
        List<ModifyCode> modifyList = new ArrayList<>();
        Map<String, List<CodeVO>> codeVOListMap = getCodeGroupMap(codeVOList);
        Set<Class<?>> enumClassList = getEnumClassList();
        for (Class<?> enumerate : enumClassList) {
            EnumCodeInfo enumCodeInfo = enumerate.getAnnotation(EnumCodeInfo.class);
            String typeCode = getTypeCode(enumerate, enumCodeInfo);
            String typeName = enumCodeInfo.typeName();

            List<CodeVO> codeList = codeVOListMap.get(typeCode);
            List<EnumVO> enumList = getEnumList(enumerate);
            Date createTs = DateUtils.getCurrentDate();

            if (codeList == null) {
                modifyList.add(new ModifyCode(CodeModifyStatusEnum.ADD, typeCode, typeName, enumList, createTs));
                continue;
            }

            createTs = Optional.ofNullable(codeList.get(0).getCreateTs()).orElse(createTs);

            if (codeList.size() != enumList.size()) {
                modifyList.add(new ModifyCode(CodeModifyStatusEnum.UPDATE, typeCode, typeName, enumList, createTs));
                continue;
            }

            for (int i = 0; i < codeList.size(); i++) {
                CodeVO codeVO = codeList.get(i);
                EnumVO enumVO = enumList.get(i);
                if (codeVO.getTypeName().equals(typeName)
                        && codeVO.getConfigCode().equals(enumVO.getCode()) && codeVO.getConfigName().equals(enumVO.getName())
                        && StringUtils.ifEmpty(codeVO.getRemark()).equals(StringUtils.ifEmpty(enumVO.getRemark()))) {
                    continue;
                }

                modifyList.add(new ModifyCode(CodeModifyStatusEnum.UPDATE, typeCode, typeName, enumList, createTs));
                break;
            }

            modifyList.add(new ModifyCode(CodeModifyStatusEnum.NORMAL, typeCode, typeName, enumList, createTs));
        }
        return modifyList;
    }

    private Map<String, List<CodeVO>> getCodeGroupMap(List<CodeVOExt> codeVOList) {
        return codeVOList.stream().collect(Collectors.groupingBy(CodeVO::getTypeCode));
    }

    private void deleteThenInsertList(ModifyCode modifyCode) {
        deleteByTypeCode(modifyCode.getTypeCode());
        insertList(modifyCode);
    }

    @Override
    public void deleteByTypeCode(String typeCode) {
        if (StringUtils.isEmpty(typeCode)) {
            throw new BusinessException("系统错误，typeCode不能为空！");
        }
        CodeEntityCondition condition = new CodeEntityCondition();
        condition.createCriteria().andTypeCodeEqualTo(typeCode);
        getDao().deleteByCondition(condition);
    }

    @Override
    public void deleteById(String id) {
        CodeEntityCondition condition = new CodeEntityCondition();
        condition.createCriteria().andIdEqualTo(id);
        getDao().deleteByCondition(condition);
    }

    private void insertList(ModifyCode modifyCode) {
        List<CodeVO> list = IntStream.range(0, modifyCode.getEnumList().size()).mapToObj(idx -> {
            EnumVO item = modifyCode.getEnumList().get(idx);
            String configCode = item.getCode();
            String configName = item.getName();
            String remark = StringUtils.ifEmpty(item.getRemark());
            short indexNo = (short) (idx + 1);

            CodeVO code = new CodeVO();
            code.setId(getCodeId(modifyCode.getTypeCode(), String.valueOf(indexNo)));
            code.setIndexNo(indexNo);
            code.setTypeCode(modifyCode.getTypeCode());
            code.setTypeName(modifyCode.getTypeName());
            code.setConfigCode(configCode);
            code.setConfigName(configName);
            code.setRemark(remark);
            code.setCreateTs(modifyCode.getCreateTs());
            code.setCreateUserId(Constants.ADMIN_USER_ID);
            code.setUpdateTs(DateUtils.getCurrentDate());
            code.setUpdateUserId(Constants.ADMIN_USER_ID);
            return code;
        }).collect(Collectors.toList());

        this.insertList(BeanUtils.copyToNewList(list, CodeEntity.class));
    }

    private String getCodeId(String typeCode, String indexNo) {
        return typeCode + "_" + indexNo;
    }

    public String getTypeCode(Class<?> enumerate, EnumCodeInfo enumCodeInfo) {
        String typeCode = null;

        if (enumCodeInfo != null) {
            typeCode = enumCodeInfo.typeCode();
        }

        if (StringUtils.isEmpty(typeCode)) {
            typeCode = StringUtils.camelCaseToSnakeCase(enumerate.getSimpleName().replace("Enum", "")).toUpperCase();
        }

        return typeCode;
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    private class ModifyCode {

        /**
         * 分类编码
         */
        private String typeCode;
        /**
         * 分类名称
         */
        private String typeName;
        /**
         *
         */
        private CodeModifyStatusEnum modifyStatus;

        private List<EnumVO> enumList;

        ModifyCode(CodeModifyStatusEnum modifyStatus) {
            this.modifyStatus = modifyStatus;
        }

        ModifyCode(CodeModifyStatusEnum modifyStatus, String typeCode, String typeName, List<EnumVO> enumList, Date createTs) {
            this.typeCode = typeCode;
            this.typeName = typeName;
            this.modifyStatus = modifyStatus;
            this.enumList = enumList;
            this.createTs = createTs;
        }

        /**
         * 创建时间
         */
        private Date createTs;

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public CodeModifyStatusEnum getModifyStatus() {
            return modifyStatus;
        }

        public void setModifyStatus(CodeModifyStatusEnum modifyStatus) {
            this.modifyStatus = modifyStatus;
        }

        public List<EnumVO> getEnumList() {
            return enumList;
        }

        public void setEnumList(List<EnumVO> enumList) {
            this.enumList = enumList;
        }

        public Date getCreateTs() {
            return createTs;
        }

        public void setCreateTs(Date createTs) {
            this.createTs = createTs;
        }


    }

}
