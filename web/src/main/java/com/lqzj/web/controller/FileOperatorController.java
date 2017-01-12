package com.lqzj.web.controller;

import com.lqzj.common.exception.Assert;
import com.lqzj.common.exception.ErrorCode;
import com.lqzj.web.model.FileType;
import com.lqzj.web.service.FileOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/file")
public class FileOperatorController {

    @Autowired
    private FileOperatorService fileOperatorService;

    @RequestMapping(value = "/upload/{type}/{name}", method = RequestMethod.POST)
    public String upload(@PathVariable("type") FileType fileType,
                         @PathVariable("name") String name,
                         @RequestParam(value = "file") MultipartFile file) {
        Assert.isTrue(!(name.contains(".") || name.contains("/")), ErrorCode.ILLEGAL_UPLOAD_NAME);
        return fileOperatorService.upload(fileType, name, file);
    }
}
