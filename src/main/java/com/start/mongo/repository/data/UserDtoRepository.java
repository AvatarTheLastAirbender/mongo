package com.start.mongo.repository.data;

import com.start.mongo.model.document.User;
import com.start.mongo.model.dto.request.UserDto;

import java.util.List;

@SuppressWarnings("unused")
public interface UserDtoRepository {
    void save(UserDto user);

    List<User> findAll();

    User findOneByUsername(String username);
}
