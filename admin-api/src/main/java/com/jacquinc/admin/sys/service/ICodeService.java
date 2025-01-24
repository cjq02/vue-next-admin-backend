package com.jacquinc.admin.sys.service;

import com.jiujie.framework.exception.BusinessException;
import com.jacquinc.admin.application.vo.EnumVO;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.vo.CodeVO;
import com.jacquinc.admin.sys.vo.CodeVOExt;

import java.util.List;
import java.util.Set;

/**
 * 码表 接口
 *
 * @author cjq
 * created on  2020/08/18
 */
public interface ICodeService {

    /**
     * 页面列表
     *
     * @param page 页面
     * @return 列表
     * @throws BusinessException 业务异常
     */
    Page<CodeVOExt, CodeVOExt> getCodePage(Page<CodeVOExt, CodeVOExt> page) throws BusinessException;

    /**
     * 枚举 页面列表
     *
     * @param page 页面
     * @return 列表
     * @throws BusinessException 业务异常
     */
    Page<CodeVOExt, CodeVOExt> getEnumPage(Page<CodeVOExt, CodeVOExt> page) throws BusinessException;

    /**
     * 码表组 页面列表
     *
     * @param page 页面
     * @return 列表
     * @throws BusinessException 业务异常
     */
    Page<CodeVOExt, CodeVOExt> getCodeGroupPage(Page<CodeVOExt, CodeVOExt> page) throws BusinessException;

    /**
     * 列表查询
     *
     * @return 列表
     * @throws BusinessException 业务异常
     */
    List<CodeVOExt> findCodeList() throws BusinessException;

    /**
     * 列表查询 根据分类代码
     *
     * @param typeCode 分类代码
     * @return 列表
     * @throws BusinessException 业务异常
     */
    List<CodeVOExt> findByTypeCode(String typeCode) throws BusinessException;

    /**
     * 根据条件获取一条记录
     *
     * @param typeCode   分类代码
     * @param configName 码表名称
     * @return 实体
     * @throws BusinessException 业务异常
     */
    CodeVO getCodeByParams(String typeCode, String configName);

    /**
     * 根据条件获取码表编码
     *
     * @param typeCode   分类代码
     * @param configName 码表名称
     * @return 码表编码
     */
    String getConfigCodeByParams(String typeCode, String configName);

    /**
     * 根据条件获取码表名称
     *
     * @param typeCode   分类代码
     * @param configCode 码表编码
     * @return 码表名称
     */
    String getConfigNameByParams(String typeCode, String configCode);

    /**
     * 保存列表
     *
     * @param list          列表
     * @param currentUserId 用户
     */
    void saveCodeList(List<CodeVOExt> list, String currentUserId);

    /**
     * 根据码表分类编码 获取枚举类
     *
     * @param typeCode 码表分类
     * @return 枚举类
     */
    Class<?> getEnumClass(String typeCode);

    /**
     * 获取枚举类列表
     *
     * @return 枚举类
     */
    Set<Class<?>> getEnumClassList();

    /**
     * 获取枚举包列表
     *
     * @return 枚举包
     */
    List<String> getEnumPackageList();

    /**
     * 枚举列表
     *
     * @param typeCode 码表分类
     * @return 列表
     */
    List<EnumVO> getEnumList(String typeCode);

    /**
     * 枚举列表
     *
     * @param enumerate 枚举
     * @return 列表
     */
    List<EnumVO> getEnumList(Class<?> enumerate);

    /**
     * 生成码表
     *
     * @return 消息
     * @throws BusinessException 异常
     */
    Boolean generateCode() throws BusinessException;

    /**
     * 生成码表
     *
     * @return 消息
     * @throws BusinessException 异常
     */
    Boolean generateCode(CodeVOExt params) throws BusinessException;

    /**
     * 根据分类编码删除
     *
     * @param typeCode 分类编码
     * @throws BusinessException 业务异常
     */
    void deleteByTypeCode(String typeCode) throws BusinessException;

    /**
     * 根据ID删除
     *
     * @param id 主键
     */
    void deleteById(String id);
}
