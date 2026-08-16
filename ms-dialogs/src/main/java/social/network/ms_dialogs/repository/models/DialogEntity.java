package social.network.ms_dialogs.repository.models;

import org.springframework.lang.NonNull;

public class DialogEntity {
    private String id;
    private String partner1;
    private String partner2;
    private String createdDate;
    private boolean deleted;

    public DialogEntity() {}

    public DialogEntity(String id, String partner1, String partner2, String createdDate, boolean deleted) {
        this.id = id;
        this.partner1 = partner1;
        this.partner2 = partner2;
        this.createdDate = createdDate;
        this.deleted = deleted;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartner1() {
        return this.partner1;
    }

    public void setPartner1(String partner1) {
        this.partner1 = partner1;
    }

    public String getPartner2() {
        return this.partner2;
    }

    public void setPartner2(String partner2) {
        this.partner2 = partner2;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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
        return String.format("Dialog:{dialogId: %s, partner1: %s, partner2: %s,  createdDate: %s, deleted: %s}",
               getId(), getPartner1(), getPartner2(), getCreatedDate(), isDeleted());
    }
}
