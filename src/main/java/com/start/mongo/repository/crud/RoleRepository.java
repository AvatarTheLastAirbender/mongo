package com.start.mongo.repository.crud;

import com.start.mongo.model.document.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Roles, String> {
    Optional<Roles> findByUserId(String s);
}
