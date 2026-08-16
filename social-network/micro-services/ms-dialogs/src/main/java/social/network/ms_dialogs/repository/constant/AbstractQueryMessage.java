package social.network.ms_dialogs.repository.constant;

import java.util.logging.Logger;

public abstract class AbstractQueryMessage {
    public static final String QUERY_SAVE = "INSERT INTO message (message_id, dialog_id, author_id, message, status, send_date, update_date, deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String QUERY_SAVE_UPDATABLE = "INSERT INTO editable (message_id, dialog_id, author_id, message, status, send_date, update_date, deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String QUERY_UPDATE = "UPDATE message SET status = ?, update_date = ? WHERE message_id = ?;";
    public static final String QUERY_UPDATE_DELETE = "UPDATE message SET message = ?, author_id = ?, update_date = ?, deleted = ? WHERE message_id = ?;";
    public static final String QUERY_GET_BY_DIALOG_ID = "SELECT * FROM message WHERE dialog_id = ?;";
    public static final String QUERY_GET_RECEIVED_UNREAD_MESSAGES = "SELECT * FROM message WHERE author_id != ? AND status = ?;";
    public static final String QUERY_GET_BY_MESSAGE_ID = "SELECT * FROM message WHERE message_id = ?;";
    public static final String QUERY_GET_UNREAD_MESSAGES = "SELECT * FROM message WHERE dialog_id = ? AND author_id != ? AND status = ?;";
    public static final String QUERY_DELETE_MESSAGE = "DELETE FROM message WHERE message_id = ?;";

    public static Logger getLogger() {
        return Logger.getLogger("Message repository logger");
    }
}
