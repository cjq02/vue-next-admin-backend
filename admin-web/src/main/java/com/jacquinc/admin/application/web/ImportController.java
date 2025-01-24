package com.jacquinc.admin.application.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jacquinc.admin.application.web.imports.AbstractImportHandler;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.application.annotation.ImportHandler;
import com.jacquinc.admin.utils.StringUtils;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.spring.context.utils.SpringContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author cjq
 * created on  2020/09/01
 */
@RestController
@RequestMapping("/import")
@Api(tags = "导入")
@ApiSort(996)
public class ImportController extends BaseController {

    private final static String IMPORTS_PACKAGE_PATH = "com.jacquinc.admin.application.web.imports";

    @HasPermission("user")
    @PostMapping(value = "/importExcel.json")
    @ApiOperation("excel导入数据")
    @ApiOperationSupport(order = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "importFile", value = "文件", dataType = "MultipartFile", required = true)
    })
    public ResponseResult importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                /*request.getParameter()*/
                String name = request.getParameter("name");
                POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                Sheet sheet = wb.getSheetAt(0);
                AbstractImportHandler abstractImportHandler = getImportHandler(name, sheet);
                abstractImportHandler.checkHead(name, sheet);
                abstractImportHandler.execute(request, sheet, getCurrentUserId());
                return new ResponseResult("导入成功！");
            }
            catch(BusinessException e) {
                return new ResponseResult(false, null, e.getMessage());
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
                if (StringUtils.isNotEmpty(e.getMessage()) && e.getMessage().contains("Your file appears not to be a valid OLE2 document")) {
                    return new ResponseResult(false, null, "请使用标准导入模版来导入数据！");
                }
                return new ResponseResult(false, null, "导入的模板有误，请校验是否满足要求：" + e.getMessage());
            }
        }
        return new ResponseResult(false, null, "未知错误！");
    }

    /**
     * 导入处理类路由，通过模板类型映射到对应的处理类
     *
     * @param type  模板类型
     * @param sheet 导入文件Excel
     * @return 错误消息
     * @throws ClassNotFoundException 类未找到
     */
    private AbstractImportHandler getImportHandler(String type, Sheet sheet) throws ClassNotFoundException {
        return (AbstractImportHandler) SpringContextUtil.getBean(getImportHandlerClass(type));
    }

    /**
     * 遍历imports目录下，先匹配注解ImportHandler type的值
     * 如果没有再匹配类名，如：ImportClazzHandler
     *
     * @param name 导入文件类型
     * @return 类
     * @throws ClassNotFoundException 类未找到
     */
    private Class<?> getImportHandlerClass(String name) throws ClassNotFoundException {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setUrls(ClasspathHelper.forPackage(IMPORTS_PACKAGE_PATH));
        builder.filterInputsBy(new FilterBuilder().include(IMPORTS_PACKAGE_PATH + ".*.class"));
        Set<Class<?>> classList = new Reflections(builder).getTypesAnnotatedWith(ImportHandler.class);

        for (Class<?> clazz : classList) {
            ImportHandler importHandler = clazz.getAnnotation(ImportHandler.class);
            if (importHandler.name().equals(name)) {
                return clazz;
            }
        }

        String className = "Import" + StringUtils.capitalize(name) + "Handler";
        return Class.forName(IMPORTS_PACKAGE_PATH + "." + className);
    }
}
