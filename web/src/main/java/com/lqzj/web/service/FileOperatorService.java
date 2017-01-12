package com.lqzj.web.service;

import com.lqzj.common.exception.Assert;
import com.lqzj.common.exception.BadRequestException;
import com.lqzj.common.exception.ErrorCode;
import com.lqzj.web.model.FileType;
import com.lqzj.web.utils.FileOperatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileOperatorService {

    private static final Logger logger = LoggerFactory.getLogger(FileOperatorService.class);

    @Value("${app.upload.paths}")
    private String[] paths;

    @Value("${app.upload.server.url}")
    private String serverUrl;

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String upload(FileType fileType, String name, MultipartFile file) {
        validServerUploadPath();
        try {
            return serverUrl + FileOperatorUtils.saveFiles(paths, fileType, name, file);
        } catch (IOException e) {
            logger.error("upload file error, fileName: [{}], error info: \n {}", file.getOriginalFilename(), e.getLocalizedMessage());
            throw new BadRequestException(ErrorCode.UPLOAD_FILE_ERROR);
        }
    }

    private void validServerUploadPath() {
        for (String path : paths) {
            Assert.isTrue(new File(path).exists(), ErrorCode.SERVER_UPLOAD_PATH_NOT_READY);
        }
    }
}
