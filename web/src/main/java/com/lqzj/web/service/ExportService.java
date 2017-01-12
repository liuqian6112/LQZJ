package com.lqzj.web.service;

import com.google.common.collect.Lists;
import com.lqzj.common.exception.BadRequestException;
import com.lqzj.common.exception.ErrorCode;
import com.lqzj.web.dao.UserDao;
import com.lqzj.web.model.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExportService {

    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);

    @Autowired
    private UserDao userDao;

    public void exportUserList(HttpServletResponse response) {
        List<User> userList = userDao.getAllUser();
        String[] headers = {"id", "name", "age", "address", "phone", "created", "password", "email"};
        String fileName = "User_List_" + new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new Date());
        exportData(headers, userList, fileName, response);
    }

    private void exportData(String[] headers, List<User> dataList, String fileName, HttpServletResponse response) {
        List<String> exportDataList = Lists.newArrayList();
        exportDataList.add(StringUtils.join(headers, ","));
        dataList.forEach(user -> exportDataList.add(transformToString(user)));
        exportFile(new File(fileName + ".csv"), exportDataList, response);
    }

    private String transformToString(User user) {
        return user.getId() + "," + user.getName() + "," + user.getAge() + "," + user.getAddress() + "," +
                user.getPhone() + "," + user.getCreated() + "," + user.getPassword() + "," + user.getEmail();
    }

    private void exportFile(File file, List<String> exportDataList, HttpServletResponse response) {
        FileInputStream inputStream = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            response.setContentType("application/octet-stream");
            FileUtils.writeLines(file, exportDataList);
            inputStream = new FileInputStream(file);
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            logger.error("export file error, error info: \n {}", e.getLocalizedMessage());
            throw new BadRequestException(ErrorCode.SYSTEM_ERROR);
        } finally {
            closeStreamAndDeleteFile(inputStream, file);
        }
    }

    private void closeStreamAndDeleteFile(FileInputStream inputStream, File file) {
        try {
            inputStream.close();
            inputStream = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        file.delete();
    }
}
