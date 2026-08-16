package social.network.ms_dialogs.repository.mapping;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import social.network.ms_dialogs.dto.ReadStatus;
import social.network.ms_dialogs.repository.models.MessageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MessageMapper implements RowMapper<MessageEntity> {
    public MessageEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MessageEntity(
                rs.getString("message_id"),
                rs.getString("dialog_id"),
                rs.getString("author_id"),
                rs.getString("message"),
                rs.getObject("status", ReadStatus.class),
                rs.getString("send_date"),
                rs.getString("update_date"),
                rs.getBoolean("deleted"));
    }
}