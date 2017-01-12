package com.lqzj.web.dao;

import com.lqzj.common.query.PageQuery;
import com.lqzj.web.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserDao {

    User getUser(@Param("id") long id);

    List<User> getUserByCreated(@Param("created") Date created);

    void saveUser(User user);

    int countUser();

    List<User> queryUser(PageQuery pageQuery);

    List<User> getAllUser();
}
