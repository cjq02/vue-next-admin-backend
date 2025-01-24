package com.jacquinc.admin.sys.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jacquinc.admin.application.annotation.EnumCodeInfo;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.utils.FreeMarkerUtils;
import com.jacquinc.admin.sys.service.ICodeService;
import com.jacquinc.admin.sys.vo.CodeVOExt;
import com.jacquinc.admin.utils.DateUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.exception.BusinessException;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
@Component
public class CodeHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.profiles}")
    private String env;

    @Autowired
    private ICodeService codeService;

    public void generateEnumJs() {
        if (!env.equals(Constants.LOCAL)) {
            return;
        }

        try {
            Configuration cfg = FreeMarkerUtils.getFreemarkerConfiguration(getTemplatePath());
            String filePath = getVueGenerateFilePath("enum.js");
            HashMap<String, Object> data = getEnumJsTemplateProperties();
            File file = new File(filePath);
            if (!file.getParentFile().exists() && file.getParentFile().mkdirs()) {
                logger.info(filePath + " 枚举文件不存在，已创建!");
            }
            FileWriter fileWriter = new FileWriter(file);
            cfg.getTemplate("enum_js.ftl").process(data, fileWriter);
            fileWriter.close();
            logger.info(filePath + " Generate enum.js Success!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成枚举ts失败!", e);
        }
    }

    public Object getCodeJsObject() {
        try {
            HashMap<String, String> map = new HashMap<>();
            Configuration cfg = FreeMarkerUtils.getFreemarkerConfiguration(getTemplatePath());
            HashMap<String, Object> data = getCodeJsTemplateProperties();
            map.put("code.json", getGenerateFileText(cfg, "code_json.ftl", data));
            map.put("code.ts", getGenerateFileText(cfg, "code_js.ftl", data));
            map.put("enum.ts", getGenerateFileText(cfg, "enum_js.ftl", getEnumJsTemplateProperties()));
            return map;
        } catch (Exception e) {
            throw new BusinessException("生成失败：" + e.getMessage());
        }
    }

    private String getGenerateFileText(Configuration cfg, String templateFileName, HashMap<String, Object> data) {
        try {
            Writer writer = new StringWriter();
            cfg.getTemplate(templateFileName).process(data, writer);
            writer.close();
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unused")
    public void generateCodeJs() {
        if (!env.equals(Constants.LOCAL)) {
            return;
        }

        try {
            Configuration cfg = FreeMarkerUtils.getFreemarkerConfiguration(getTemplatePath());
            String codeJsFilePath = getVueGenerateFilePath("code.ts");
            String codeMapFilePath = getVueGenerateFilePath("code.json");
            HashMap<String, Object> data = getCodeJsTemplateProperties();

            File file = new File(codeJsFilePath);
            if (!file.getParentFile().exists() && file.getParentFile().mkdirs()) {
                logger.info(codeJsFilePath + " 码表文件不存在，已创建!");
            }
            FileWriter fileWriter = new FileWriter(file);
            cfg.getTemplate("code_js.ftl").process(data, fileWriter);
            fileWriter.close();

            file = new File(codeMapFilePath);
            if (!file.getParentFile().exists() && file.getParentFile().mkdirs()) {
                logger.info(codeJsFilePath + " 码表映射文件不存在，已创建!");
            }
            fileWriter = new FileWriter(file);
            cfg.getTemplate("code_json.ftl").process(data, fileWriter);

            fileWriter.close();
            logger.info(codeJsFilePath + " Generate code.js && code.json Success!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成码表js失败!", e);
        }
    }

    @SuppressWarnings("rawtypes")
    private HashMap<String, Object> getEnumJsTemplateProperties() {
        HashMap<String, Object> map = Maps.newHashMap();
        List list = codeService.getEnumClassList().stream().map(enumerate -> {
            HashMap<String, Object> enumMap = Maps.newHashMap();
            EnumCodeInfo enumCodeInfo = enumerate.getAnnotation(EnumCodeInfo.class);
            List codeList = codeService.getEnumList(enumerate);
            enumMap.put("name", StringUtils.uncapitalize(enumerate.getSimpleName().replace("Enum", "")));
            enumMap.put("desc", enumCodeInfo.typeName());
            enumMap.put("codeList", codeList);
            return enumMap;
        }).sorted(Comparator.comparing(m -> m.get("name").toString())).collect(Collectors.toList());

        map.put("list", list);
        return map;
    }

    private HashMap<String, Object> getCodeJsTemplateProperties() {
        HashMap<String, Object> map = Maps.newHashMap();
        Map<String, List<CodeVOExt>> codeGroup = codeService.findCodeList().stream().collect(groupingBy(CodeVOExt::getTypeCode));
        codeGroup.entrySet().removeIf(item -> Constants.GENERATE_CODE_IGNORE_LIST.contains(item.getKey()));
        Iterator<Map.Entry<String, List<CodeVOExt>>> iterator = codeGroup.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<CodeVOExt>> entry = iterator.next();
            for (String machStr : Constants.GENERATE_CODE_IGNORE_LIKE_LIST) {
                if (entry.getKey().contains(machStr)) {
                    iterator.remove();
                }
            }
        }
        List<HashMap<String, Object>> list = Lists.newArrayList();
        List<HashMap<String, Object>> finalList = list;
        codeGroup.forEach((key, item) -> {
            HashMap<String, Object> codeMap = Maps.newHashMap();
            codeMap.put("name", String.valueOf(key));
            codeMap.put("desc", item.get(0).getTypeName());
            codeMap.put("codeList", item);
            finalList.add(codeMap);
        });
        list = list.stream().sorted(Comparator.comparing(m -> m.get("name").toString())).collect(Collectors.toList());
        map.put("list", list);
        return map;
    }

    public String getTemplatePath() {
        return "/static/template/";
    }

    private String getVueGenerateFilePath(String fileName) throws IOException {
        return new ClassPathResource(".").getURL().getPath() + fileName;
    }

    public HashMap<String, Object> getEnumClassTemplateProperties(CodeVOExt codeVO) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("package", codeVO.getPackagePath());
        map.put("packagePrefix", Constants.PACKAGE_PREFIX);
        map.put("date", DateUtils.dateToString(DateUtils.getCurrentDate()));
        map.put("enumClassName", codeVO.getEnumClassName());
        map.put("typeCode", codeVO.getTypeCode());
        map.put("typeName", codeVO.getTypeName());
        map.put("hasRemark", Constants.YES.equals(codeVO.getHasRemark()));
        map.put("list", getCodeList(codeVO));
        return map;
    }

    @SuppressWarnings("rawtypes")
    private List getCodeList(CodeVOExt codeVO) {
        List<CodeVOExt> codeList = codeService.findByTypeCode(codeVO.getTypeCode());
        return codeList.stream().map(vo -> {
            Map<String, String> enumMap = Maps.newHashMap();
            enumMap.put("key", (Character.isDigit(vo.getConfigCode().charAt(0)) ? "_" : "") + vo.getConfigCode());
            enumMap.put("code", vo.getConfigCode());
            enumMap.put("name", vo.getConfigName());
            enumMap.put("remark", vo.getRemark());
            return enumMap;
        }).collect(Collectors.toList());
    }

    public String getEnumClassFilePath(String packagePath, String enumClassName) {
        String classPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
        String projectDir = classPath.replace("-web/target/classes", "-api/src/main/java");
        return projectDir + packagePath.replace(".", "/") + "/" + enumClassName + ".java";
    }

    public String getEnumPackagePath(String typeCode) {
        Class<?> enumClass = codeService.getEnumClass(typeCode);
        if (enumClass == null) {
            return "";
        }
        return enumClass.getName();
    }

    public void generateEnumClass(CodeVOExt vo) {
        try {
            Configuration cfg = FreeMarkerUtils.getFreemarkerConfiguration(getTemplatePath());
            String filePath = getEnumClassFilePath(vo.getPackagePath(), vo.getEnumClassName());
            HashMap<String, Object> data = getEnumClassTemplateProperties(vo);
            File file = new File(filePath);
            if (!file.getParentFile().exists() && file.getParentFile().mkdirs()) {
                logger.info(filePath + " 枚举文件不存在，已创建!");
            }
            FileWriter fileWriter = new FileWriter(file);
            cfg.getTemplate("enum_class.ftl").process(data, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("生成枚举失败!", e);
        }
    }

}
