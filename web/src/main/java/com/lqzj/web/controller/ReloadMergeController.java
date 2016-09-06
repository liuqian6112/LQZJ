package com.lqzj.web.controller;

import com.lqzj.web.service.ReloadMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reload/merge")
public class ReloadMergeController {

    @Autowired
    private ReloadMergeService reloadMergeService;

    @RequestMapping(value = "/{num}", method = RequestMethod.GET)
    public void reloadMerge(@PathVariable("num") int num) {
        reloadMergeService.reloadMerge(num);
    }
}
