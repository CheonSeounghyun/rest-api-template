package com.wini.restapitemplate.user.service;

import com.wini.restapitemplate.user.mapper.UserMapper;
import com.wini.restapitemplate.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers(User user) {
        return userMapper.getAllUser(user);
    }

    public User getUser(String id) {
        return userMapper.getUser(id);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int deleteUser(String userId) {
        return userMapper.deleteUser(userId);
    }
}
