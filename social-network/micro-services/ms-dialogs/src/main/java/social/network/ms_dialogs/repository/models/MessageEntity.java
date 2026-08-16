package social.network.ms_dialogs.repository.models;

import org.springframework.lang.NonNull;
import social.network.ms_dialogs.dto.ReadStatus;

public class MessageEntity {

    private String id;

    private String dialogId;

    private String message;

    private String author;

    private ReadStatus status;

    private String createdDate;

    private String updatedDate;

    private boolean deleted;

    public MessageEntity() {
    }

    public MessageEntity(String id, String dialogId, String author, String message, ReadStatus status,
                         String createdDate, String updatedDate, boolean deleted) {
        this.id = id;
        this.dialogId = dialogId;
        this.author = author;
        this.message = message;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deleted = deleted;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDialogId() {
        return this.dialogId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStatus(ReadStatus status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public ReadStatus getStatus() {
        return this.status;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Message: {id: %s, dialogId: %s, author: %s, message: %s, createdDate: %s, updatedDate: %s, status: %s, deleted: %s}",
                getId(), getDialogId(), getAuthor(), getMessage(), getCreatedDate(), getUpdatedDate(), getStatus(), isDeleted());
    }
}