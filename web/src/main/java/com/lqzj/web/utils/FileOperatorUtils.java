package com.lqzj.web.utils;

import com.google.common.io.Files;
import com.lqzj.common.exception.Assert;
import com.lqzj.common.exception.ErrorCode;
import com.lqzj.web.model.FileType;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileOperatorUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileOperatorUtils.class);

    public static String saveFiles(String[] basePaths, FileType fileType, String name, MultipartFile file) throws IOException {
        Assert.isTrue(file != null && !file.isEmpty(), ErrorCode.UPLOAD_FILE_EMPTY);
        byte[] data = file.getBytes();

        String fileName = DigestUtils.sha1Hex(data) + ((new Date()).getTime() / 1000) + "."
                + Files.getFileExtension(file.getOriginalFilename());
        Assert.isTrue(saveFiles(basePaths, fileType, name, fileName, data) > 0, ErrorCode.UPLOAD_FILE_ERROR);
        return fileType.getFolder() + "/" + name + "/" + fileName;
    }

    private static int saveFiles(String[] basePaths, FileType fileType, String name, String fileName, byte[] data) {
        int successCount = 0;
        for (String basePath : basePaths) {
            try {
                saveFile(basePath, fileType, name, fileName, data);
                successCount++;
            } catch (IOException e) {
                logger.error("save file error, fileName: [{}], error info: \n {}", fileName, e.getLocalizedMessage());
            }
        }
        return successCount;
    }

    private static void saveFile(String basePath, FileType fileType, String name, String fileName, byte[] data) throws IOException {
        File file = new File(basePath, fileType.getFolder() + "/" + name);
        if (!file.exists()) {
            file.mkdirs();
        }
        Files.write(data, file);
    }
}
