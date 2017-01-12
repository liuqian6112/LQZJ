package com.lqzj.web.controller;

import com.lqzj.web.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/export")
public class ExportController {
    @Autowired
    private ExportService exportService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void exportUserList(HttpServletResponse response) {
        exportService.exportUserList(response);
    }
}
