package social.network.ms_account.notification;

import social.network.ms_account.model.dto.NotificationDto;

@FunctionalInterface
public interface NotificationsService {

    void sendNotification(NotificationDto notification);
}