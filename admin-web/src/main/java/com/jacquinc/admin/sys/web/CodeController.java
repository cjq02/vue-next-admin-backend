package com.jacquinc.admin.sys.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.google.common.collect.Maps;
import com.jacquinc.admin.sys.handler.CodeHandler;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.vo.EnumVO;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.application.web.BaseController;
import com.jacquinc.admin.sys.service.ICodeService;
import com.jacquinc.admin.sys.vo.CodeVO;
import com.jacquinc.admin.sys.vo.CodeVOExt;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.JSONUtils;
import com.jiujie.framework.cache.cache.ICacheClient;
import com.jiujie.framework.spring.web.bind.annotation.JsonPathParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 码表
 *
 * @author cjq
 * created on  2020/08/18
 */
@SuppressWarnings({"unchecked", "SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
@RestController
@RequestMapping("sys/code")
@Api(tags = "码表管理")
@ApiSort(999)
public class CodeController extends BaseController {

    @Autowired
    private ICodeService codeService;
    @Autowired
    private ICacheClient cacheClient;
    @Autowired
    private CodeHandler codeHandler;

    /**
     * 获取码表列表
     *
     * @param typeCode 码表分类代码
     * @return 码表列表
     */
    @HasPermission("Code:view")
    @GetMapping("/list.json")
    @ApiOperation("码表列表")
    @ApiOperationSupport(order = 1)
    public List<CodeVO> findCodeList(String typeCode) {
        return getCodeGroupMapFromCache().get(typeCode);
    }

    /**
     * 码表
     *
     * @param typeCode 码表分类代码
     * @return 码表
     */
    @HasPermission("Code:view")
    @GetMapping("/map.json")
    @ApiOperation("码表映射")
    @ApiOperationSupport(order = 2)
    public Map<String, Object> codeMap(String typeCode) {
        List<CodeVO> codeVOList = getCodeGroupMapFromCache().get(typeCode);
        Map<String, Object> map = Maps.newHashMap();
        for (CodeVO codeVO : codeVOList) {
            map.put(codeVO.getConfigCode(), codeVO.getConfigName());
        }
        return map;
    }

    /**
     * 码表缓存
     *
     * @return 码表
     */
    @HasPermission("user")
    @GetMapping("/getCodeGroupMapFromCache.json")
    @ApiOperation("码表缓存")
    @ApiOperationSupport(order = 3)
    public Map<String, List<CodeVO>> getCodeGroupMapFromCache() {
        Map<String, List<CodeVO>> codeGroupMap = (Map<String, List<CodeVO>>) cacheClient.get(Constants.CACHE_GROUP, Constants.CODE_LIST_CACHE);
        if (codeGroupMap == null) {
            codeGroupMap = codeService.findCodeList().stream().collect(Collectors.groupingBy(CodeVO::getTypeCode));
            cacheClient.put(Constants.CACHE_GROUP, Constants.CODE_LIST_CACHE, codeGroupMap);
        }
        return codeGroupMap;
    }

    @HasPermission("Code:edit")
    @GetMapping("/reloadCodeCache.json")
    @ApiOperation("更新缓存")
    @ApiOperationSupport(order = 4)
    public ResponseResult reloadCodeCache() {
        cacheClient.remove(Constants.CACHE_GROUP, Constants.CODE_LIST_CACHE);
        getCodeGroupMapFromCache();
        return new ResponseResult("更新缓存成功！");
    }

    /**
     * 列表
     *
     * @param page 页面参数
     * @return 页面信息
     */
    @HasPermission("user")
    @PostMapping("/getCodePage.json")
    @ApiOperation("码表分页")
    @ApiOperationSupport(order = 5)
    public Page<CodeVOExt, CodeVOExt> getCodePage(@RequestBody Page<CodeVOExt, CodeVOExt> page) {
        return codeService.getCodePage(page);
    }

    /**
     * 保存
     *
     * @param list 实体
     * @return 响应结果
     */
    @HasPermission("Code:edit")
    @PostMapping("/saveCodeList.json")
    @ApiOperation("保存列表")
    @ApiOperationSupport(order = 6)
    public ResponseResult saveCodeList(@RequestBody List<CodeVOExt> list) {
        codeService.saveCodeList(JSONUtils.objectToList(list, CodeVOExt.class), getCurrentUserId());
        reloadCodeCache();
        return new ResponseResult("保存成功");
    }

    /**
     * 枚举分页
     *
     * @param page 页面参数
     * @return 页面信息
     */
    @HasPermission("user")
    @PostMapping("/getEnumPage.json")
    @ApiOperation("枚举分页")
    @ApiOperationSupport(order = 7)
    public Page<CodeVOExt, CodeVOExt> getEnumPage(@JsonPathParam("$.pageInfo") Page<CodeVOExt, CodeVOExt> page) {
        return codeService.getEnumPage(page);
    }

    /**
     * 枚举列表
     *
     * @param typeCode 码表编码
     * @return 页面信息
     */
    @HasPermission("user")
    @GetMapping("/findEnumList.json")
    @ApiOperation("枚举列表")
    @ApiOperationSupport(order = 8)
    public List<EnumVO> findEnumList(String typeCode) {
        return codeService.getEnumList(typeCode);
    }

    /**
     * 枚举包列表
     *
     * @return 页面信息
     */
    @HasPermission("Code:view")
    @GetMapping("/findEnumPackageList.json")
    @ApiOperation("枚举包列表")
    @ApiOperationSupport(order = 9)
    public List<String> findEnumPackageList() {
        return codeService.getEnumPackageList();
    }

    /**
     * 码表组 页面
     *
     * @param page 页面参数
     * @return 页面信息
     */
    @HasPermission("Code:view")
    @PostMapping("/getCodeGroupPage.json")
    @ApiOperation("码表组分页")
    @ApiOperationSupport(order = 10)
    public Page<CodeVOExt, CodeVOExt> getCodeGroupPage(@RequestBody Page<CodeVOExt, CodeVOExt> page) {
        Page<CodeVOExt, CodeVOExt> pageInfo = codeService.getCodeGroupPage(page);
        pageInfo.getRecords().forEach(item -> {
            CodeVOExt vo = (CodeVOExt) item;
            if (Constants.GENERATE_CODE_IGNORE_LIST.contains(vo.getTypeCode())) {
                vo.setIsDynamicConfig(Constants.YES);
                return;
            }
            long matchCount = Constants.GENERATE_CODE_IGNORE_LIKE_LIST.stream()
                    .filter(prefixCode -> vo.getTypeCode().startsWith(prefixCode)).count();
            if (matchCount > 0) {
                vo.setIsDynamicConfig(Constants.YES);
            }
        });
        return pageInfo;
    }

    /**
     * 生成码表并刷新缓存
     */
    public void generateCodeThenReloadCache() {
        codeService.generateCode();
        reloadCodeCache();
    }

    @HasPermission("Code:edit")
    @GetMapping("/generateCode.json")
    @ApiOperation("生成码表(根据参数)")
    @ApiOperationSupport(order = 12)
    public ResponseResult generateCode(Boolean isDev) {
        generateCodeThenReloadCache();
        return new ResponseResult("生成码表成功！", isDev ? codeHandler.getCodeJsObject() : null);
    }


    /**
     * 根据分类代码删除
     *
     * @param typeCode 分类编码
     * @return 响应结果
     */
    @HasPermission("Code:delete")
    @PostMapping("/deleteByTypeCode.json")
    @ApiOperation(value = "删除码表(根据typeCode)")
    @ApiOperationSupport(order = 13)
    public ResponseResult deleteByTypeCode(String typeCode) {
        codeService.deleteByTypeCode(typeCode);
        reloadCodeCache();
        return new ResponseResult("删除成功");
    }

    /**
     * 根据id删除
     *
     * @param id 主键
     * @return 响应结果
     */
    @HasPermission("Code:delete")
    @PostMapping("/deleteById.json")
    @ApiOperation(value = "删除码表(根据id)")
    @ApiOperationSupport(order = 14)
    public ResponseResult deleteById(String id) {
        codeService.deleteById(id);
        reloadCodeCache();
        return new ResponseResult("删除成功");
    }

    /**
     * 获取枚举包名
     *
     * @param typeCode 分类编码
     * @return 响应结果
     */
    @HasPermission("Code:view")
    @GetMapping("/getEnumPackageName.json")
    @ApiOperation(value = "获取枚举包名")
    @ApiOperationSupport(order = 15)
    public ResponseResult getEnumPackageName(String typeCode) {
        return new ResponseResult(null, "获取枚举包名成功", codeHandler.getEnumPackagePath(typeCode));
    }

    /**
     * 生成枚举
     *
     * @param vo 实体
     * @return 响应结果
     */
    @HasPermission("Code:edit")
    @PostMapping("/generateEnum.json")
    @ApiOperation(value = "生成枚举")
    @ApiOperationSupport(order = 16)
    public ResponseResult generateEnumClass(@RequestBody CodeVOExt vo) {
        codeHandler.generateEnumClass(vo);
        codeHandler.generateEnumJs();
        return new ResponseResult("生成枚举成功!");
    }
}
