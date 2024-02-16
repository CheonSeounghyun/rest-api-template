package com.wini.restapitemplate.user.mapper;

import com.wini.restapitemplate.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUser(User user);

    int insertUser(User user);
}
