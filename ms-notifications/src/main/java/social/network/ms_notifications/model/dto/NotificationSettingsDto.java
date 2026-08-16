package social.network.ms_notifications.model.dto;

import java.util.UUID;

public record NotificationSettingsDto (
        UUID id, //unique number of user
        boolean enablePost, // on/off property get notifications of news
        boolean enablePostComment, // on/off property get notifications of comments for news
        boolean enableCommentComment, // on/off property get answer for comments
        boolean enableFriendRequest, // on/off property get notification for add friend
        boolean enableFriendBirthday, // on/off property get notification of friends birthday
        boolean enableMessage, // on/off property get notification of send messages
        boolean enableSendEmailMessage // on/off get notification of message to email
) {
}