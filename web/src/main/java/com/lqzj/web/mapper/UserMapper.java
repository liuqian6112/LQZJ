package com.lqzj.web.mapper;

import com.lqzj.common.mapper.ModelMapper;
import com.lqzj.web.model.People;
import com.lqzj.web.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends ModelMapper {
    public UserMapper() {
        super();
        registerUser();
        registerPeople();
    }

    private void registerUser() {
        this.mapperFactory.classMap(User.class, People.class)
                .field("name", "username")
                .field("password", "password")
                .field("email", "email")
                .field("created", "addCreated")
                .register();
    }

    private void registerPeople() {
        this.mapperFactory.classMap(People.class, User.class)
                .field("username", "name")
                .field("password", "password")
                .field("email", "email")
                .field("addCreated", "created")
                .register();
    }
}
