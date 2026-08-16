package social.network.ms_notifications.storage;

import social.network.ms_notifications.model.entity.NotificationEntity;
import social.network.ms_notifications.model.entity.SettingsEntity;
import social.network.ms_notifications.model.entity.UpdateEntity;

import java.util.List;
import java.util.UUID;

public interface ApplicationStorage {

    NotificationEntity saveNotification(NotificationEntity entity);

    List<NotificationEntity> getNotifications(UUID accountId);

    SettingsEntity saveSettings(SettingsEntity entity);

    SettingsEntity getSettings(UUID accountId);

    SettingsEntity editSettings(UpdateEntity entity);
}