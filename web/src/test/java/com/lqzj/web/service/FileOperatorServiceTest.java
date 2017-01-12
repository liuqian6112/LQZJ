package com.lqzj.web.service;

import com.google.common.io.Files;
import com.lqzj.web.model.FileType;
import com.lqzj.web.service.base.FileOperatorTestBase;
import org.junit.*;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

@Ignore
public class FileOperatorServiceTest extends FileOperatorTestBase {

    private String[] paths = new String[]{"file1", "file2"};

    private String serverUrl = "http://www.soho3q.com/images/";

    private FileOperatorService fileOperatorService;

    @Before
    public void setUp() throws Exception {
        fileOperatorService = new FileOperatorService();
        fileOperatorService.setServerUrl(serverUrl);
    }

    @After
    public void tearDown() {
        deleteDirs(paths);
    }

    @Test
    public void should_return_url_when_correct_param() throws Exception {
        String[] savePaths = createBaseDir(paths);
        fileOperatorService.setPaths(savePaths);
        File file = newFile();
        MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "multipart/form-data", Files.toByteArray(file));

        String url = fileOperatorService.upload(FileType.PAGE, "title", multipartFile);
        Assert.assertSame(serverUrl + getExpectFileName(FileType.PAGE, "title", file), url);
        file.deleteOnExit();
    }
}
