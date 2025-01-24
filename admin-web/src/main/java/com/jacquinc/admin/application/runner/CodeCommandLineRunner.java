package com.jacquinc.admin.application.runner;

import com.jacquinc.admin.sys.web.CodeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
@Lazy
public class CodeCommandLineRunner implements CommandLineRunner {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CodeController codeController;

    @Override
    public void run(String... strings) {
        try {
            codeController.generateCodeThenReloadCache();
            logger.info("Running Generate Code Process Completed");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Generate Code Error!");
        }
    }
}
