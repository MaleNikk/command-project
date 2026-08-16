package social.network.ms_notifications.storage;

import social.network.ms_notifications.model.entity.NotificationEntity;

@FunctionalInterface
public interface NotificationStorage {

    boolean sendNotification(NotificationEntity entity);
}