package com.jacquinc.admin;

import com.jacquinc.admin.utils.UploadUtils;
import com.jiujie.framework.web.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@ServletComponentScan
@SpringBootApplication
@ImportResource({
        "classpath:/META-INF/spring/appCtx-consumer.xml"
})
public class ApplicationAdminWeb extends ServletContainer {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationAdminWeb.class);

    @Bean
    public UploadUtils uploadUtils() {
        return new UploadUtils();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationAdminWeb.class, args);
        logger.info("启动完成！");
    }
}