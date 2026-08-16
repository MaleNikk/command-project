package social.network.ms_notifications.model.dto;

import java.util.Date;

public record NotificationCountDto(Date timestamp, NotificationCount data) {
}
// timestamp - date-time of string
// data - int count: counts notifications for user