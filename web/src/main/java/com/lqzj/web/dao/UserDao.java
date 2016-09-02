package com.lqzj.web.dao;

import com.lqzj.web.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User getUser(@Param("id") long id);
}
