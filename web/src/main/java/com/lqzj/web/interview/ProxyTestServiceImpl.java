package com.lqzj.web.interview;

import com.google.common.collect.Lists;
import com.lqzj.web.dao.PeopleDao;
import com.lqzj.web.dao.UserDao;
import com.lqzj.web.model.People;
import com.lqzj.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author liuqian1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProxyTestServiceImpl implements ProxyTestService {

    private static int i = 0;
    private static int j = 0;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PeopleDao peopleDao;

    @Autowired
    private ProxyTestServiceExt proxyTestServiceExt;

    @Autowired
    private ApplicationContext applicationContext;

    private ProxyTestService proxyTestService;

    @PostConstruct
    public void setProxyTestService() {
        proxyTestService = applicationContext.getBean(ProxyTestService.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void set() {
        User user = new User();
        user.setName("liuqian");
        user.setPassword("liuqian");
        userDao.saveUser(user);
        List<User> allUser = userDao.getAllUser();
        allUser.forEach(user1 -> System.out.println(user1.getName()));
//        ((ProxyTestService) AopContext.currentProxy()).get(); // 无法使用
//        get();
//        proxyTestServiceExt.get();// 挪到另一个接口
        proxyTestService.get();//初始化注入
//        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public String get() {
        People people = new People();
        people.setUsername("zj");
        people.setPassword("zj");
        peopleDao.savePeoples(Lists.newArrayList(people));

        return "hello";
    }

}
