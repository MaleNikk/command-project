package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

public record MessageDto(String id, String time, String conversationPartner1, String conversationPartner2,
                         String messageText, ReadStatus readStatus, String dialogId, boolean deleted) {
    @NonNull
    public String toString() {
        return String.format("Message: {id: %s, time: %s, conversationPartner1: %s, conversationPartner2: %s, messageText: %s, readStatus: %s, dialogId: %s, deleted: %s}",
                id(),time(),conversationPartner1(),conversationPartner2(),messageText(),readStatus(),dialogId(),deleted());
    }
}
