package com.lqzj.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class People {
    private int id;
    private String username;
    private String password;
    private String email;
    private Date addCreated;
}
