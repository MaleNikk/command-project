package social.network.ms_dialogs.repository.models;

import org.springframework.lang.NonNull;
import social.network.ms_dialogs.dto.ReadStatus;

import java.util.Date;

public record ResponseData(Date date, String conversationPartner1, String conversationPartner2,
                           String messageText, ReadStatus readStatus, String dialogId, String id) {

    @NonNull
    @Override
    public String toString() {
        return String.format("ResponseData: {date: %s, conversationPartner1: %s, conversationPartner2: %s, messageText: %s, readStatus: %s, dialogId: %s, id: %s}",
                date(), conversationPartner1(), conversationPartner2(), messageText(), readStatus(), dialogId(), id());
    }
}
