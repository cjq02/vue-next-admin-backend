package com.jacquinc.admin.utils;

import com.jacquinc.admin.application.constants.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadUtils {

    private static String profiles;

    private static String fileUploadPath;

    private static String textFileUploadPath;

    private static String thumbnailPathKey;

    @Value("${spring.profiles}")
    public void setProfiles(String profiles) {
        UploadUtils.profiles = profiles;
    }

    @Value("${app.fileUploadPath:}")
    public void setFileUploadPath(String fileUploadPath) {
        UploadUtils.fileUploadPath = fileUploadPath;
    }

    @Value("${app.textFileUploadPath:}")
    public void setTextFileUploadPath(String textFileUploadPath) {
        UploadUtils.textFileUploadPath = textFileUploadPath;
    }

    @Value("${app.thumbnailPathKey:}")
    public void setThumbnailPathKey(String thumbnailPathKey) {
        UploadUtils.thumbnailPathKey = thumbnailPathKey;
    }

    public static String fileRootPath() {
        if (Constants.LOCAL.equals(profiles)) {
            return System.getProperty("user.dir").substring(0, System.getProperty("user.dir").
                    lastIndexOf(System.getProperty("os.name").toLowerCase().startsWith("windows") ? "\\" : "/")) + fileUploadPath;
        }
        return fileUploadPath;
    }

    public static String textFileRootPath() {
        if (Constants.LOCAL.equals(profiles)) {
            return System.getProperty("user.dir").substring(0, System.getProperty("user.dir").
                    lastIndexOf(System.getProperty("os.name").toLowerCase().startsWith("windows") ? "\\" : "/")) + textFileUploadPath;
        }
        return textFileUploadPath;
    }

    public static String thumbnailPath(String filePath) {
        String osValue = System.getProperty("os.name").toLowerCase().startsWith("windows") ? "\\" : "/";
        return filePath.substring(0, filePath.lastIndexOf(osValue) + 1) + thumbnailPathKey + osValue;
    }

    public static String thumbnailFilePath(String filePath) {
        String osValue = System.getProperty("os.name").toLowerCase().startsWith("windows") ? "\\" : "/";
        return filePath.substring(0, filePath.lastIndexOf(osValue) + 1) + thumbnailPathKey +
                filePath.substring(filePath.lastIndexOf(osValue));
    }

    public static String getSuffix(String fileName) {
        int beginIndex = fileName.lastIndexOf(".");
        if (beginIndex < 0) {
            return "";
        }
        return fileName.substring(beginIndex);
    }
}