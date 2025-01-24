package com.jacquinc.admin.application.utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author cjq
 */
public class FreeMarkerUtils {

    private static Configuration configuration = null;

    /**
     * 获取 Freemarker 模板环境配置
     *
     * @return 配置
     */
    public static Configuration getFreemarkerConfiguration(String templatePath) {
        if (configuration == null) {
            configuration = initFreemarkerConfiguration(templatePath);
        }
        return configuration;
    }

    /**
     * Freemarker 模板环境配置
     *
     * @return 配置
     */
    private static Configuration initFreemarkerConfiguration(String templatePath) {
        Configuration cfg;
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(FreeMarkerUtils.class, templatePath);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

}
