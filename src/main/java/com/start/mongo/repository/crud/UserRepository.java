package com.start.mongo.repository.crud;

import com.start.mongo.model.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findOneByUsername(String username);

    Optional<User> findOneByEmail(String email);
}
