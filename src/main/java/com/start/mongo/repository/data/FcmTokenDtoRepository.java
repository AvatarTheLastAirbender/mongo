package com.start.mongo.repository.data;

import com.start.mongo.model.document.FcmToken;
import com.start.mongo.model.dto.notification.FcmTokenDto;

@SuppressWarnings("unused")
public interface FcmTokenDtoRepository {
    void save(FcmTokenDto fcmTokenDto);

    void update(FcmTokenDto fcmTokenDto);

    FcmToken findByUserId(String userId);
}
