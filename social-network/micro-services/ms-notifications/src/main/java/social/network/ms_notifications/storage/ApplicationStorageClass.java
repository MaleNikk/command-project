package social.network.ms_notifications.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;
import social.network.ms_notifications.model.entity.NotificationEntity;
import social.network.ms_notifications.model.entity.SettingsEntity;
import social.network.ms_notifications.model.entity.UpdateEntity;
import social.network.ms_notifications.logging.ApplicationLogging;
import social.network.ms_notifications.mapping.RowMapperNotification;
import social.network.ms_notifications.mapping.RowMapperSettings;

import java.util.List;
import java.util.UUID;

@Repository
public class ApplicationStorageClass extends QueryConstant implements ApplicationStorage {

    private final ApplicationLogging logging;

    private final JdbcTemplate jdbcTemplate;

    private final RowMapperNotification mapperNotification;

    private final RowMapperSettings mapperSettings;

    @Autowired
    public ApplicationStorageClass(ApplicationLogging logging, JdbcTemplate jdbcTemplate,
                                   RowMapperNotification mapperNotification, RowMapperSettings mapperSettings) {
        this.logging = logging;
        this.jdbcTemplate = jdbcTemplate;
        this.mapperNotification = mapperNotification;
        this.mapperSettings = mapperSettings;
    }

    @Override
    public NotificationEntity saveNotification(NotificationEntity entity) {
        logging.printDebug("Application storage class: call method save notification.");
        return DataAccessUtils.singleResult(jdbcTemplate.query(SAVE_NOTIFICATION, ps -> {
            ps.setObject(1, entity.id());
            ps.setObject(2, entity.accountId());
            ps.setString(3, entity.content());
            ps.setString(4, entity.notificationType().name());
            ps.setObject(5, entity.sentTime());
            ps.setObject(6, entity.receiverId());
            ps.setString(7, entity.serviceType().name());
            ps.setObject(8, entity.eventId());
            ps.setBoolean(9, entity.isReaded());
        }, mapperNotification));
    }

    @Override
    public List<NotificationEntity> getNotifications(UUID accountId) {
        logging.printDebug("Application storage class: call method get notification by account id.");
        return jdbcTemplate.query(GET_NOTIFICATIONS, mapperNotification);
    }

    @Override
    public SettingsEntity saveSettings(SettingsEntity entity) {
        logging.printDebug("Application storage class: call method save settings.");
        return DataAccessUtils.singleResult(jdbcTemplate.query(SAVE_SETTINGS, ps -> {
            ps.setObject(1, entity.accountId());
            ps.setBoolean(2, entity.enableNotifications());
            ps.setBoolean(3, entity.enablePost());
            ps.setBoolean(4, entity.enablePostComment());
            ps.setBoolean(5, entity.enableCommentComment());
            ps.setBoolean(6, entity.enableFriendRequest());
            ps.setBoolean(7, entity.enableFriendBirthday());
            ps.setBoolean(8, entity.enableMessage());
        }, mapperSettings));
    }

    @Override
    public SettingsEntity getSettings(UUID accountId) {
        logging.printDebug("Application storage class: call method get settings by account id.");
        return DataAccessUtils.singleResult(jdbcTemplate.query(GET_SETTINGS,
                new ArgumentPreparedStatementSetter(new Object[]{accountId}),
                new RowMapperResultSetExtractor<>(mapperSettings, 1)));
    }

    @Override
    public SettingsEntity editSettings(UpdateEntity entity) {
        logging.printDebug("Application storage class: call method edit settings.");
        return DataAccessUtils.singleResult(jdbcTemplate.query(EDIT_SETTINGS, (ps) ->
        {
            ps.setString(1,entity.type().name().toLowerCase());
            ps.setBoolean(2, entity.enable());
            ps.setObject(3, entity.accountId());
        }, mapperSettings));
    }
}