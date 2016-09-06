package com.lqzj.web.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private long id;
    private String name;
    private Integer age;
    private String address;
    private String phone;
    private String password;
    private String email;
    private Date created;
}
