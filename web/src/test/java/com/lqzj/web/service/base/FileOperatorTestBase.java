package com.lqzj.web.service.base;

import com.google.common.io.Files;
import com.lqzj.web.model.FileType;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

public class FileOperatorTestBase {

    protected File newFile() throws IOException {
        File file = new File("./", "test.txt");
        Files.touch(file);
        Files.append("test content", file, Charset.forName("UTF-8"));
        return file;
    }

    protected String[] createBaseDir(String[] paths) {
        String[] savePaths = new String[paths.length];
        for (int i = 0; i < paths.length; i++) {
            File file = new File(paths[i]);
            if (!file.exists()) {
                file.mkdirs();
            }
            savePaths[i] = file.getAbsolutePath();
        }
        return savePaths;
    }

    protected String getExpectFileName(FileType fileType, String name, File file) throws Exception {
        byte[] data = Files.toByteArray(file);
        if (data == null || data.length == 0) {
            throw new NullPointerException("byte array can't be null");
        }
        return fileType.getFolder() + "/" + name + "/" + DigestUtils.sha1Hex(data) + (new Date().getTime() / 1000) + "."
                + Files.getFileExtension(file.getName());
    }

    protected void deleteDirs(String[] paths) {
        for (String path : paths) {
            FileUtils.deleteQuietly(new File(path));
        }
    }
}
