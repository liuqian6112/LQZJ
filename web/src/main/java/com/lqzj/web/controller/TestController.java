package com.lqzj.web.controller;

import com.lqzj.web.interview.ProxyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuqian1
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProxyTestService proxyTestService;

    @RequestMapping("/set")
    public void set() {
        proxyTestService.set();
    }

}
