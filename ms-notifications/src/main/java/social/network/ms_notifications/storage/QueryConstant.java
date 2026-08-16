package social.network.ms_notifications.storage;

public class QueryConstant {

    public final static String SAVE_NOTIFICATION, GET_NOTIFICATIONS, SAVE_SETTINGS, GET_SETTINGS, EDIT_SETTINGS;

    static {
        SAVE_NOTIFICATION = "INSERT INTO notifications.notification (id, account_id, content, notification_type, sent_time, receiver_id, service_type, event_id, is_reed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        GET_NOTIFICATIONS = "SELECT * FROM notifications.notification WHERE account_id = ?;";
        SAVE_SETTINGS = "INSERT INTO notifications.settings (id, enable_notifications, enable_post, enable_post_comment, enable_comment_comment, enable_friend_request, enable_friend_birthday, enable_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        GET_SETTINGS = "SELECT * FROM notification.settings WHERE id = ?;";
        EDIT_SETTINGS = "UPDATE notifications.settings SET %s = ? WHERE id = ?;";
    }
}