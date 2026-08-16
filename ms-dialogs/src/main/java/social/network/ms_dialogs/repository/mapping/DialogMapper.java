package social.network.ms_dialogs.repository.mapping;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import social.network.ms_dialogs.repository.models.DialogEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DialogMapper implements RowMapper<DialogEntity> {
    public DialogEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DialogEntity(
                rs.getString("dialog_id"),
                rs.getString("person_id_1"),
                rs.getString("person_id_2"),
                rs.getString("created_date"),
                rs.getBoolean("deleted"));
    }
}