package com.lqzj.web.controller;

import com.lqzj.web.service.MergeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merge")
public class MergeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MergeController.class);

    @Autowired
    private MergeService mergeService;

    @RequestMapping(value = "/{num}", method = RequestMethod.GET)
    public String mergeNum(@PathVariable("num") Integer num) {
        LOGGER.info("merge num : {}", num);
        return mergeService.mergeNum(num);
    }
}
