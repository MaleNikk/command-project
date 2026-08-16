package social.network.ms_notifications.model.entity;

import java.util.UUID;

public record SettingsEntity(
        UUID accountId,
        boolean enableNotifications,
        boolean enablePost,
        boolean enablePostComment,
        boolean enableCommentComment,
        boolean enableFriendRequest,
        boolean enableFriendBirthday,
        boolean enableMessage
) {

    public static SettingsEntity getInstance(UUID id) {
        return new SettingsEntity(
                id,
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE

        );
    }
}