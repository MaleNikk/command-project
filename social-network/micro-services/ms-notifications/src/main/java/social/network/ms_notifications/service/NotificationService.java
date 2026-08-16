package social.network.ms_notifications.service;

import social.network.ms_notifications.model.entity.NotificationEntity;

@FunctionalInterface
public interface NotificationService {

    boolean sendNotification(NotificationEntity entity);
}