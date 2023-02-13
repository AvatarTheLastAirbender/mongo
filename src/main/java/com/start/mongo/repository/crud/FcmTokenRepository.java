package com.start.mongo.repository.crud;


import com.start.mongo.model.document.FcmToken;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface FcmTokenRepository extends MongoRepository<FcmToken, String> {
    FcmToken findByUserId(String id);
}
