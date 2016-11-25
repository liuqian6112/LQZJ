package com.lqzj.web.controller;

import com.lqzj.common.annotation.RequestLogger;
import com.lqzj.common.query.PageQuery;
import com.lqzj.common.wrapper.PageResponseWrapper;
import com.lqzj.web.model.User;
import com.lqzj.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
    public String get(@PathVariable("key") String key) {
        LOGGER.info("get key:{} from redis", key);
        return userService.get(key);
    }

    @RequestMapping(value = "/set/{key}/{value}", method = RequestMethod.GET)
    public void set(@PathVariable("key") String key, @PathVariable("value") String value) {
        LOGGER.info("set key:{}, value:{}", key, value);
        userService.set(key, value);
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") long id) {
        LOGGER.info("get user by id:{}", id);
        return userService.getUser(id);
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @RequestLogger
    public void saveUser(@RequestBody User user) throws IOException {
        userService.saveUser(user);
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public PageResponseWrapper<User> getAllUser(@Valid
                                 @ModelAttribute PageQuery pageQuery) {

        return userService.query(pageQuery);
    }
}
