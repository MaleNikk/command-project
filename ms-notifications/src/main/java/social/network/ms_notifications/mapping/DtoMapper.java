package social.network.ms_notifications.mapping;

import social.network.ms_notifications.model.dto.NotificationDto;
import social.network.ms_notifications.model.dto.NotificationSettingsDto;
import social.network.ms_notifications.model.dto.NotificationUpdateDto;
import social.network.ms_notifications.model.dto.PageModelNotificationsDto;
import social.network.ms_notifications.model.entity.NotificationEntity;
import social.network.ms_notifications.model.entity.SettingsEntity;
import social.network.ms_notifications.model.entity.UpdateEntity;

import java.util.List;

public final class DtoMapper {

    public static NotificationDto from(NotificationEntity entity) {
        return null;
    }

    public static NotificationEntity from(NotificationDto notification) {
        return new NotificationEntity(
                notification.id(),
                notification.authorId(),
                notification.content(),
                notification.notificationType(),
                notification.sentTime(),
                notification.receiverId(),
                notification.serviceType(),
                notification.eventId(),
                notification.isReed()
        );
    }

    public static NotificationSettingsDto from(SettingsEntity entity) {
        return null;
    }

    public static NotificationEntity from(NotificationSettingsDto settings) {
        return null;
    }

    public static UpdateEntity from(NotificationUpdateDto update) {
        return null;
    }

    public static PageModelNotificationsDto from(List<NotificationEntity> entities) {
        return null;
    }
}