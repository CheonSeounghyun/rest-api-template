package com.wini.restapitemplate.user.mapper;

import com.wini.restapitemplate.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAllUser(User user);

    User getUser(String id);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(String userId);
}
