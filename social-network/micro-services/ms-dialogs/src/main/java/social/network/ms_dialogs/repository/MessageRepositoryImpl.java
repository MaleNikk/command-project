package social.network.ms_dialogs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import social.network.ms_dialogs.dto.ReadStatus;
import social.network.ms_dialogs.repository.constant.AbstractQueryMessage;
import social.network.ms_dialogs.repository.mapping.MessageMapper;
import social.network.ms_dialogs.repository.models.MessageEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

@Repository
@Qualifier
public class MessageRepositoryImpl extends AbstractQueryMessage implements MessageRepository {
    private final JdbcTemplate template;
    private final MessageMapper mapper;

    @Autowired
    public MessageRepositoryImpl(JdbcTemplate template, MessageMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public List<MessageEntity> getMessagesByDialog(String dialogId) {
        getLogger().info("Init method getMessagesByDialog (return List<MessageEntity>) in MessageRepository.");
        return new ArrayList<>(template.query(
                QUERY_GET_BY_DIALOG_ID,
                (ps) -> ps.setString(1, dialogId),
                mapper));
    }

    public List<MessageEntity> getUnreadMessagesByDialogIdAndPersonId(String dialogId, String personId) {
        return new ArrayList<>(template.query(
                QUERY_GET_UNREAD_MESSAGES,
                (ps) -> {
                    ps.setString(1, dialogId);
                    ps.setString(2, personId);
                    ps.setString(3, String.valueOf(ReadStatus.SENT));
                },
                mapper));
    }

    public MessageEntity save(String message, String authorId, String dialogId) {
        getLogger().info("Init method save (return MessageEntity) in MessageRepository.");
        String id = getId();
        template.update(
                QUERY_SAVE, (ps) ->
                {
                    ps.setString(1, id);
                    ps.setString(2, dialogId);
                    ps.setString(3, authorId);
                    ps.setString(4, message);
                    ps.setString(5, ReadStatus.SENT.name());
                    ps.setString(6, getCurrentDate());
                    ps.setString(7, null);
                    ps.setBoolean(8, false);
                });
        return getMessageById(id);
    }

    public MessageEntity update(String messageId, String cause, String authorId) {
        getLogger().info("Init method delete (return boolean) in MessageRepository.");
        MessageEntity saved = getMessageById(messageId);
        template.update(QUERY_SAVE_UPDATABLE, (ps) ->
        {
            ps.setString(1, saved.getId());
            ps.setString(2, saved.getDialogId());
            ps.setString(3, saved.getAuthor());
            ps.setString(4, saved.getMessage());
            ps.setString(5, saved.getStatus().name());
            ps.setString(6, saved.getCreatedDate());
            ps.setString(7, getCurrentDate());
            ps.setBoolean(8, true);
        });

        template.update(QUERY_UPDATE_DELETE, (ps) ->
        {
            ps.setString(1, cause);
            ps.setString(2, authorId);
            ps.setString(3, getCurrentDate());
            ps.setBoolean(4, true);
            ps.setString(5, messageId);
        });
        return getMessageById(messageId);
    }

    public void batchUpdate(String dialogId, String personId) {
        getLogger().info("Init method update in MessageRepository.");
        List<MessageEntity> entities = getUnreadMessagesByDialogIdAndPersonId(dialogId, personId);
        template.batchUpdate(QUERY_UPDATE, new BatchPreparedStatementSetter() {
            public void setValues(@NonNull PreparedStatement ps, int i) throws SQLException {
                MessageEntity message = entities.get(i);
                ps.setString(1, ReadStatus.READ.name());
                ps.setString(2, getCurrentDate());
                ps.setString(3, message.getId());
            }

            public int getBatchSize() {
                return entities.size();
            }
        });
    }

    public long getCountReceivedUnreadMessages(String partnerId) {
        getLogger().info("Init method getCountReceivedUnreadMessages in MessageRepository.");
        return template.query(
                QUERY_GET_RECEIVED_UNREAD_MESSAGES,
                (ps) -> {
                    ps.setString(1, partnerId);
                    ps.setString(2, ReadStatus.SENT.name());
                },
                mapper).size();
    }

    public boolean deleteMessage(String messageId) {
        getLogger().info("Init method deleteMessage in MessageRepository.");
        template.update(QUERY_DELETE_MESSAGE, messageId);
        return getMessageById(messageId) == null;
    }

    public MessageEntity getMessageById(String messageId) {
        getLogger().info("Init method getMessageById (return MessageEntity) in MessageRepository.");
        return DataAccessUtils.singleResult(
                template.query(
                        QUERY_GET_BY_MESSAGE_ID,
                        new ArgumentPreparedStatementSetter(new Object[]{messageId}),
                        mapper));
    }

    private String getId() {
        return String.valueOf(UUID.randomUUID());
    }

    private String getCurrentDate() {
        return Date.from(Instant.now()).toString();
    }
}
