package com.start.mongo.model.dto.notification;

import com.start.mongo.helper.NotificationPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Android {
    private String ttl;
    private NotificationPriority priority;
    private String restrictedPackageName;
    private AndroidNotification notification;
}
