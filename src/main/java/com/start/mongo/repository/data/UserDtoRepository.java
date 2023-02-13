package com.start.mongo.repository.data;

import com.start.mongo.model.document.User;
import com.start.mongo.model.dto.request.SignUp;

import java.util.List;

@SuppressWarnings("unused")
public interface UserDtoRepository {
    void save(SignUp user);

    List<User> findAll();

    User findOneByUsername(String username);
}
