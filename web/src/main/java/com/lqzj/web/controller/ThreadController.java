package com.lqzj.web.controller;

import com.lqzj.web.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread")
public class ThreadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadController.class);

    @Autowired
    private ThreadService threadService;

    @RequestMapping(value = "merge/{num}", method = RequestMethod.GET)
    public String mergeNum(@PathVariable("num") Integer num) {
        LOGGER.info("merge num : {}", num);
        return threadService.mergeNum(num);
    }
}
