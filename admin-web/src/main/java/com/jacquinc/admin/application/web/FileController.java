package com.jacquinc.admin.application.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jacquinc.admin.common.util.MD5Utils;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.DateUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.base.utils.UUIDUtils;
import com.jiujie.framework.exception.BusinessException;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.sys.service.IFileService;
import com.jacquinc.admin.sys.vo.FileVO;
import com.jacquinc.admin.sys.vo.FileVOExt;
import com.jacquinc.admin.utils.UploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by zhengzheng on 2019/5/22.
 */
@RestController
@RequestMapping("/file")
@Api(tags = "附件")
@ApiSort(998)
public class FileController extends BaseController {

    @Value("${app.openOfficeHome}")
    private String openOfficeHome;

    @Value("${app.openOfficePath}")
    private String openOfficePath;

    @Autowired
    private IFileService fileService;

    /**
     * 附件上传
     *
     * @param file
     * @param request
     * @param response
     * @return
     */
    @HasPermission("user")
    @PostMapping(value = "/upload.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperationSupport(order = 1)
    @ApiOperation("上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", required = true),
    })
    public ResponseResult uploads(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "file") MultipartFile file, FileVOExt condition) {
        // 上传附件
        FileVO fileVO = uploadFile(request, response, file, condition);
        if (fileVO == null) {
            throw new BusinessException("上传失败，请联系管理员！");
        }
        Map<String, Object> map = new HashMap<>();
        fileVO = fileService.save(fileVO, getCurrentUserId());
        map.put("id", fileVO.getId());
        map.put("fileName", fileVO.getFileName());
        return new ResponseResult("文件上传成功！", map);
    }

    /**
     * 富文本附件上传
     *
     * @param file
     * @param request
     * @param response
     * @return
     */
    @HasPermission("user")
    @PostMapping(value = "/textUpload.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperationSupport(order = 2)
    @ApiOperation("富文本附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", required = true),
    })
    public ResponseResult textUpload(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "file") MultipartFile file) {
        // 上传附件
        String path = textUploadFile(request, response, file);
        if (path == null) {
            throw new BusinessException("上传失败，请联系管理员！");
        }
        return new ResponseResult(true, null, null, path);
    }

    private String textUploadFile(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws BusinessException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "POST");
            String oldFileName = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            logger.info("文件上传，原始文件名：" + oldFileName);
            logger.info("文件上传，文件大小:" + size + "字节");
            logger.info("文件上传，文件类型:" + contentType);
            //1.获取项目的全路径
            //2.获取文件名，最后一个"."前面的名字
            String suffix = UploadUtils.getSuffix(oldFileName);
            //3.文件名直接使用uuid进行保存
            String fileName = System.currentTimeMillis() + RandomStringUtils.random(6, false, true) + suffix;
            //4.根据字符串生成文件目录
            String dateStr = DateUtils.getDate2NumberString(new Date());
            File dirFile = new File(UploadUtils.textFileRootPath(), dateStr);
            if (!dirFile.exists() && !dirFile.mkdirs()) {
                throw new BusinessException("创建多层文件夹失败");
            }
            //5.生成文件
            File destFile = new File(dirFile, fileName);
            file.transferTo(destFile);
            return "/" + dateStr + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @HasPermission("user")
    @RequestMapping(method = RequestMethod.GET, value = "/download.json")
    @ApiOperationSupport(order = 3)
    @ApiOperation("下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "附件id", dataType = "String", required = true),
    })
    public void download(String id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        FileVO fileVO = fileService.getById(id);
        //设置文件路径
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + UriUtils.encode(fileVO.getFileName(), "UTF-8"));
        response.setHeader("Content-Length", String.valueOf(new File(fileVO.getFileUrl()).length()));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(fileVO.getFileUrl()));
            int i;
            while ((i = bis.read(buff)) > 0) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    @HasPermission("user")
    @RequestMapping(method = RequestMethod.POST, value = "/deleteById.json")
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "附件id", dataType = "String", required = true),
    })
    public ResponseResult deleteById(String id) {
        return fileService.deleteById(id);
    }

    private FileVO uploadFile(HttpServletRequest request, HttpServletResponse response, MultipartFile file, FileVOExt condition) throws BusinessException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "POST");
            String oldFileName = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            logger.info("文件上传，原始文件名：" + oldFileName);
            logger.info("文件上传，文件大小:" + size + "字节");
            logger.info("文件上传，文件类型:" + contentType);
            //1.获取项目的全路径
            //2.获取文件名，最后一个"."前面的名字
            String suffix = UploadUtils.getSuffix(oldFileName);
            //3.文件名直接使用uuid进行保存
            String fileName = UUIDUtils.generate() + suffix;
            //4.根据字符串生成文件目录
            String dateStr = DateUtils.getDate2NumberString(new Date());
            File dirFile = new File(UploadUtils.fileRootPath(), dateStr);
            if (!dirFile.exists() && !dirFile.mkdirs()) {
                throw new BusinessException("创建多层文件夹失败");
            }
            //5.生成文件
            File destFile = new File(dirFile, fileName);
            file.transferTo(destFile);
            //6.将文件保存到服务器相应的目录位置
            String serverFilePath = destFile.getCanonicalPath();
            String md5HashCode = MD5Utils.md5HashCode32(new FileInputStream(destFile));
            return generateVo(oldFileName, serverFilePath, size, md5HashCode, condition);
            //8.生成vo，用于保存
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private FileVO generateVo(String fileName, String fileUrl, long fileSize, String md5HashCode, FileVOExt condition) {
        FileVO fileVO = new FileVO();
        fileVO.setFileName(fileName);
        fileVO.setFileUrl(fileUrl);
        fileVO.setFileSize(fileSize);
        fileVO.setFileType(UploadUtils.getSuffix(fileName).replace(".", "").toUpperCase());
        fileVO.setCreateUserId(getCurrentUserId());
        fileVO.setCreateTs(DateUtils.getCurrentDate());
        fileVO.setHashCode(md5HashCode);
        if (condition != null && StringUtils.isNotEmpty(condition.getBizId())) {
            fileVO.setBizId(condition.getBizId());
        }
        if (condition != null && StringUtils.isNotEmpty(condition.getBizType())) {
            fileVO.setBizType(condition.getBizType());
        }
        return fileVO;
    }

    /**
     * 下拉列表
     *
     * @param bizId 业务ID
     * @return 页面信息
     */
    @HasPermission("user")
    @RequestMapping(value = "/findListByBizId.json", method = RequestMethod.GET)
    @ApiOperation("bizId下拉列表")
    @ApiOperationSupport(order = 5)
    public List<FileVO> findListByBizId(String bizId) {
        return fileService.findListByBizId(bizId);
    }

    /**
     * 根据条件获取文件列表
     *
     * @param condition 查询条件
     * @return 页面信息
     */
    @HasPermission("user")
    @RequestMapping(value = "/findFileList.json", method = RequestMethod.POST)
    public List<FileVO> findListByBizId(@RequestBody FileVOExt condition) {
        return fileService.findList(condition);
    }

    /**
     * @param response
     * @param id
     * @throws IOException
     */
    @HasPermission("user")
    @GetMapping("/preview.json")
    @ApiOperation("预览")
    @ApiOperationSupport(order = 6)
    public String preview(HttpServletResponse response, @RequestParam String id) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        FileVO fileVO = fileService.getById(id);
        if (fileVO == null) {
            return "该附件不存在，请刷新页面重试！";
        }
        /* 根据项目所在的服务器环境,确定路径中的 /  和 \ */
        String filePath = fileVO.getFileUrl();
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName) || Pattern.matches("Mac.*", osName)) {
            filePath = filePath.replace("\\", "/");
        } else if (Pattern.matches("Windows.*", osName)) {
            filePath = filePath.replace("/", "\\");
        }
        /* 获得文件名后缀 */
        String ext = fileVO.getFileName().split("\\.")
                [fileVO.getFileName().split("\\.").length - 1].toUpperCase();
        /* 根据文件类型不同进行预览 */
        /* 预览图片 */
        if ("PNG".equals(ext) || "JPEG".equals(ext) || "JPG".equals(ext)) {
            response.setContentType("image/jpeg");
        }
        /* 预览BMP格式的文件 */
        if ("BMP".equals(ext)) {
            response.setContentType("image/bmp");
        }
        /* 预览pdf */
        if ("PDF".equals(ext)) {
            response.setContentType("application/pdf");
        }
        // 利用openOffice将office文件转换为pdf格式, 然后预览doc, docx, xls, xlsx, ppt, pptx
        if ("DOC".equals(ext) || "DOCX".equals(ext) || "XLS".equals(ext) || "XLSX".equals(ext) ||
                "PPT".equals(ext) || "PPTX".equals(ext)) {
            // filePath在数据库中是不带文件后缀的, 由于jodConverter必须要识别后缀,所以将服务器中的文件重命名为带后缀的文件
            File docFile = new File(filePath);
            // 转换之后的文件名
            File pdfFile = new File(openOfficePath +
                    docFile.getPath().split("/")[docFile.getPath().split("/").length - 1] + ".pdf");
            // 判断即将要转换的文件是否真实存在
            if (docFile.exists()) {
                response.setContentType("application/pdf");
                if (!pdfFile.exists()) {
                    DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
                    configuration.setOfficeHome(new File(openOfficeHome));
                    configuration.setPortNumber(8100);
                    configuration.setTaskExecutionTimeout(1000 * 60);
                    configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24);
                    OfficeManager officeManager = configuration.buildOfficeManager();
                    officeManager.start();
                    OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
                    converter.getFormatRegistry();
                    try {
                        converter.convert(docFile, pdfFile);
                        // 文件转换之后的路径
                        filePath = pdfFile.getPath();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        officeManager.stop();
                    }
                } else {
                    filePath = pdfFile.getPath(); // 文件已经转换过
                }
            } else {
                return "需要预览的文档在服务器中不存在！";
            }
        }
        /* 将文件写入输出流,显示在界面上,实现预览效果 */
        FileInputStream fis = new FileInputStream(filePath);
        OutputStream os = response.getOutputStream();
        try {
            int count;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
                fis.close();
            }
        }
        return null;
    }
}

