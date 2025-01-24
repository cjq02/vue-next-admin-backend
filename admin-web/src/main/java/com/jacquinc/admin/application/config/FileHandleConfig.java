package com.jacquinc.admin.application.config;

import com.jacquinc.admin.utils.UploadUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zzheng
 * @company 厦门九价
 * @create 2022-04-24 3:47 下午
 */
@Configuration
public class FileHandleConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:" + UploadUtils.textFileRootPath() + "/", "classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        super.addResourceHandlers(registry);
    }
}
