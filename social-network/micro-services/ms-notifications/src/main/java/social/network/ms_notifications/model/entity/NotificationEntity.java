package social.network.ms_notifications.model.entity;

import social.network.ms_notifications.model.dto.NotificationType;
import social.network.ms_notifications.model.dto.ServiceType;

import java.util.Date;
import java.util.UUID;

public record NotificationEntity(
        UUID id,
        UUID accountId,
        String content,
        NotificationType notificationType,
        Date sentTime,
        UUID receiverId,
        ServiceType serviceType,
        UUID eventId,
        boolean isReaded
) {
    @Override
    public String toString() {
        return String.format("%s.%s.%s.%s.%s.%s.%s.%s.%s",
                id(),
                accountId(),
                content(),
                notificationType(),
                sentTime(),
                receiverId(),
                serviceType(),
                eventId(),
                isReaded());
    }
}