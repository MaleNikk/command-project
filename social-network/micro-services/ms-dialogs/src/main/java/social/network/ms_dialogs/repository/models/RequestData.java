package social.network.ms_dialogs.repository.models;

import org.springframework.lang.NonNull;
import social.network.ms_dialogs.dto.ReadStatus;

public record RequestData(String time, String conversationPartner1, String conversationPartner2,
                          String messageText, ReadStatus readStatus, String id) {

    @NonNull
    @Override
    public String toString() {
        return String.format("MessageData: {time: %s, conversationPartner1: %s, conversationPartner2: %s,  messageText: %s, readStatus: %s, id: %s}",
               time(), conversationPartner1(), conversationPartner2(), messageText(), readStatus(), id());
    }
}