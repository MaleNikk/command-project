package social.network.ms_notifications.model.dto;

public record UpsertNotificationSettingRequest (boolean enable, NotificationType notificationType) {}