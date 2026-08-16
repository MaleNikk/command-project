package social.network.ms_friends.storage;

public class StorageQueries {

    public static final String
            QUERY_SAVE,
            QUERY_EDIT_STATUS,
            QUERY_BLOCK,
            QUERY_GET_BY_FRIENDS,
            QUERY_GET_BY_STATUS,
            QUERY_GET_ALL,
            QUERY_GET_RECOMMENDS;

    static {
        QUERY_SAVE = "INSERT INTO relationship (id, friend_1, current_status, friend_2, previous_status, rating, block_author, time_register, time_update) VALUES (?,?,?,?,?,?,?,?,?);";
        QUERY_GET_BY_FRIENDS = "SELECT * FROM relationship WHERE (friend_1 = ? AND friend_2 = ?) OR (friend_2 = ? AND friend_1 = ?);";
        QUERY_GET_BY_STATUS = "SELECT * FROM relationship WHERE friend_1 = ? AND friend_2 = ? AND status = ?;";
        QUERY_EDIT_STATUS = "UPDATE relationship SET (current_status, previous_status, time_update ) VALUES (?, ?, ?) WHERE id = ?;";
        QUERY_BLOCK = "UPDATE relationship SET (current_status, previous_status, time_update, block_author) VALUES (?, ?, ?, ?) WHERE id = ?;";
        QUERY_GET_ALL = "SELECT * FROM relationship WHERE friend_1 = ? OR friend_2 = ?;";
        QUERY_GET_RECOMMENDS = "SELECT * FROM relationship WHERE friend_1 = ? OR friend_2 = ?";
    }
}