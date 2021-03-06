package com.lqzj.web.service;

import com.lqzj.common.exception.Assert;
import com.lqzj.common.exception.ErrorCode;
import com.lqzj.common.query.PageQuery;
import com.lqzj.common.service.StringRedisService;
import com.lqzj.common.wrapper.PageResponseWrapper;
import com.lqzj.web.dao.UserDao;
import com.lqzj.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private StringRedisService redisService;

    @Autowired
    private UserDao userDao;

    public String get(String key) {
        return redisService.get(key);
    }

    public void set(String key, String value) {
        redisService.save(key, value, 1L);
    }

    public User getUser(long id) {
        User user = userDao.getUser(id);
        Assert.notNull(user.getAddress(), ErrorCode.PARAMETER_NOT_NULL);
        return user;
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public PageResponseWrapper<User> query(PageQuery pageQuery) {
        int count = userDao.countUser();
        List<User> users;

        if (count > 0) {
            users = userDao.queryUser(pageQuery);
        } else {
            users = new ArrayList<>();
        }

        return new PageResponseWrapper<>(count, users, pageQuery.getPageSize(), pageQuery.getPageIndex());
    }
}
