package social.network.ms_dialogs.repository;

import social.network.ms_dialogs.repository.models.MessageEntity;

import java.util.List;

public interface MessageRepository {
    List<MessageEntity> getMessagesByDialog(String dialogId);

    List<MessageEntity> getUnreadMessagesByDialogIdAndPersonId(String dialogId, String personId);

    MessageEntity save(String message, String authorId, String dialogId);

    MessageEntity update(String messageId, String cause, String authorId);

    MessageEntity getMessageById(String messageId);

    void batchUpdate(String dialogId, String personId);

    long getCountReceivedUnreadMessages(String personId);

    boolean deleteMessage(String messageId);
}