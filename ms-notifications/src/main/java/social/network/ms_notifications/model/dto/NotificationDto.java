package social.network.ms_notifications.model.dto;

import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.UUID;

public record NotificationDto (
        UUID id,
        UUID authorId,
        String content,
        NotificationType notificationType,
        Date sentTime,
        UUID receiverId,
        ServiceType serviceType,
        UUID eventId,
        boolean isReed ){

    @NonNull
    @Override
    public String toString() {
        return String.format("NotificationDto: {id:%s, authorId:%s, content:%s, notificationType:%s, sentTime:%s, receiverId:%s, serviceType:%s, eventId:%s, isReed:%s}",
                id(), authorId(), content(), notificationType(), sentTime(), receiverId(), serviceType(), eventId(), isReed());
    }
}