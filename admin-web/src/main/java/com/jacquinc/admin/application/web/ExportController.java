package com.jacquinc.admin.application.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jacquinc.admin.application.utils.HtmlToExcelUtil;
import com.jacquinc.admin.application.annotation.HasPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * Created by zhengzheng on 2019/6/17.
 */
@RestController
@RequestMapping("/export")
@Api(tags = "导出")
@ApiSort(997)
public class ExportController extends BaseController {

    private static final String CHARSET_NAME = "UTF-8";

    @HasPermission("user")
    @PostMapping(value = "/htmlToExcel.json")
    @ApiOperation("导出")
    @ApiOperationSupport(order = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "htmlContent", value = "导出内容", dataType = "String", required = true),
            @ApiImplicitParam(name = "fileName", value = "导出文件名", dataType = "String", required = true),
    })
    public void htmlToExcel(HttpServletRequest request, HttpServletResponse response, String htmlContent, String fileName) throws Exception {
        logger.info("导出Excel:{}", fileName);
        // 生成excel
        HSSFWorkbook wb = HtmlToExcelUtil.table2Excel(htmlContent);
        // 各浏览器基本都支持ISO编码
        fileName = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
        //设置文件头
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        //设置内容类型为excel
        response.setContentType("application/vnd.ms-excel");
        response.flushBuffer();
        ServletOutputStream out = response.getOutputStream();
        wb.write(out);
        response.flushBuffer();
        out.close();
        request.getInputStream().close();
    }
}
