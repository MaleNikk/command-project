package social.network.ms_notifications.mapping;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import social.network.ms_notifications.model.entity.NotificationEntity;
import social.network.ms_notifications.model.dto.NotificationType;
import social.network.ms_notifications.model.dto.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class RowMapperNotification implements RowMapper<NotificationEntity> {
    @Override
    public NotificationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new NotificationEntity(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("account_id"),
                rs.getString("content"),
                NotificationType.valueOf(rs.getString("notification_type")),
                Date.from((Instant) rs.getObject("sent_time")),
                (UUID) rs.getObject("receiver_id"),
                ServiceType.valueOf(rs.getString("service_type")),
                (UUID) rs.getObject("event_id"),
                rs.getBoolean("is_reed")
        );
    }
}