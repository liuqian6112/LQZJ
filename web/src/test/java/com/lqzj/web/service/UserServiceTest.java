package com.lqzj.web.service;

import com.lqzj.web.dao.UserDao;
import com.lqzj.web.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_get_user_when_query_by_id() {
        User user1 = new User();
        user1.setAddress("china");
        when(userDao.getUser(1L)).thenReturn(user1);
        User user2 = userService.getUser(1L);
        assertNotNull(user2);
    }
}
