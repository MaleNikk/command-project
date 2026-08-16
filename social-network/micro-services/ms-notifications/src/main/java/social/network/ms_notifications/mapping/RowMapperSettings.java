package social.network.ms_notifications.mapping;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import social.network.ms_notifications.model.entity.SettingsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class RowMapperSettings implements RowMapper<SettingsEntity> {
    @Override
    public SettingsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SettingsEntity(
                (UUID) rs.getObject("id"),
                rs.getBoolean("enable_notifications"),
                rs.getBoolean("enable_post"),
                rs.getBoolean("enable_post_comment"),
                rs.getBoolean("enable_comment_comment"),
                rs.getBoolean("enable_friend_request"),
                rs.getBoolean("enable_friend_birthday"),
                rs.getBoolean("enable_message")
        );
    }
}