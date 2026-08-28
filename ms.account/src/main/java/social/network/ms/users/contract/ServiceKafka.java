package social.network.ms.users.contract;

import social.network.ms.users.model.dto.NotificationDto;

import java.util.UUID;

public interface ServiceKafka {

    UUID sendNotification(NotificationDto notification);

    NotificationDto readNotification(UUID key);
}