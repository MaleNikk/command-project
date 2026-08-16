package social.network.ms_notifications.model.dto;

public record NotificationUpdateDto(
        boolean enable, // on/off get notification for type message
        SettingsType type // type notification
) {
}