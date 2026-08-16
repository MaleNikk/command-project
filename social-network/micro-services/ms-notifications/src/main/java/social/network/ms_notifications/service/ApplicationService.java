package social.network.ms_notifications.service;

import social.network.ms_notifications.model.dto.*;

import java.util.UUID;

public interface ApplicationService {

    boolean sendNotificationToKafka(NotificationDto notification);

    NotificationSettingsDto getCurrentSettings(UUID id);

    NotificationSettingsDto updateCurrentSettings(NotificationUpdateDto update);

    NotificationStatus onOffNotification(UUID id);

    boolean createSettingsForNotifications(UUID id);

    PageModelNotificationsDto getPagesNotifications(UUID id);
    NotificationCountDto getCountNotification(UUID id);
}