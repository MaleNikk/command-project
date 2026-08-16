package social.network.ms_notifications.model.entity;

import social.network.ms_notifications.model.dto.SettingsType;

import java.util.UUID;

public record UpdateEntity(
        UUID accountId,
        boolean enable,
        SettingsType type
) {
}