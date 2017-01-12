package com.lqzj.web.service;

import com.lqzj.web.dao.PeopleDao;
import com.lqzj.web.dao.UserDao;
import com.lqzj.web.mapper.UserMapper;
import com.lqzj.web.model.People;
import com.lqzj.web.model.User;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);

    // 处理线程默认休眠时间
    private static final int DEFAULT_INTERVAL = 10 * 60 * 1000;//10分钟

    @Autowired
    private UserDao userDao;

    @Autowired
    private PeopleDao peopleDao;

    @Autowired
    private UserMapper userMapper;

    @Scheduled(fixedDelay = DEFAULT_INTERVAL)
    public void copyUserToPeople() {
        Date now = new Date();
        List<User> userList = userDao.getUserByCreated(DateUtils.addMinutes(now, -10));
        LOGGER.info("copy user to people, time : {}, data: {}", now, userList);
        if (!CollectionUtils.isEmpty(userList)) {
            List<People> peopleList = userList.stream().map(user -> userMapper.map(user, People.class)).collect(Collectors.toList());
            peopleDao.savePeoples(peopleList);
        }
    }

}
