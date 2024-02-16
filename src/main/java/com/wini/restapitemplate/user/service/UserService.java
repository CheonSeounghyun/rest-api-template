package com.wini.restapitemplate.user.service;

import com.wini.restapitemplate.user.mapper.UserMapper;
import com.wini.restapitemplate.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(User user) {
        return userMapper.getUser(user);
    }

    public int insertUser(User user) { return userMapper.insertUser(user);}
}
