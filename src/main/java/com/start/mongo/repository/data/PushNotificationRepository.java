package com.start.mongo.repository.data;

import com.start.mongo.model.dto.notification.Notification;
import com.start.mongo.model.dto.notification.PushNotificationResponse;

@SuppressWarnings({"unused"})
public interface PushNotificationRepository {
    PushNotificationResponse sendPushNotification(Notification notification, String delayTime);
}
