package com.start.mongo.repository.data;


import com.start.mongo.model.document.FcmToken;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface FcmTokenRepository extends MongoRepository<FcmToken, String> {
    FcmToken findByUserId(String id);
}
