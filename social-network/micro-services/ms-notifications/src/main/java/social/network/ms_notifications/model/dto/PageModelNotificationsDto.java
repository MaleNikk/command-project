package social.network.ms_notifications.model.dto;

import java.util.List;

public record PageModelNotificationsDto(
        List<NotificationsDto> content,
        PageMetaData page
) {
}
