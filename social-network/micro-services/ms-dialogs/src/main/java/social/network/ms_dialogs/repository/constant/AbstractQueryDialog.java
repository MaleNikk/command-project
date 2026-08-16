package social.network.ms_dialogs.repository.constant;

import java.util.logging.Logger;

public abstract class AbstractQueryDialog {
    public static final String QUERY_SAVE = "INSERT INTO dialog (dialog_id, person_id_1, person_id_2, created_date, deleted) VALUES (?, ?, ?, ?, ?);";
    public static final String QUERY_GET_BY_ID = "SELECT * FROM dialog WHERE dialog_id = ?";
    public static final String QUERY_GET_BY_NAMES = "SELECT * FROM dialog WHERE person_id_1 = ? OR person_id_2 = ?";
    public static final String QUERY_DELETE_BY_ID = "DELETE FROM dialog WHERE dialog_id = ?";
    public static final String QUERY_GET_BY_PARTNERS = "SELECT * FROM dialog WHERE (person_id_1 = ? AND person_id_2 = ?) OR (person_id_2 = ? AND person_id_1 = ?)";

    public static Logger getLogger() {
        return Logger.getLogger("Dialog repository logger");
    }
}
