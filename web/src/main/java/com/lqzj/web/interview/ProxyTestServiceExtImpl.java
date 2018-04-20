package com.lqzj.web.interview;

import com.google.common.collect.Lists;
import com.lqzj.web.dao.PeopleDao;
import com.lqzj.web.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuqian1
 */
@Service
public class ProxyTestServiceExtImpl implements ProxyTestServiceExt {

    @Autowired
    private PeopleDao peopleDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public String get() {
        People people = new People();
        people.setUsername("zhengjuan");
        people.setPassword("zhengjuan");
        peopleDao.savePeoples(Lists.newArrayList(people));

        return "hello";
    }
}
