package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

import java.util.List;

public record DialogDto(String id, Integer unreadCount, String conversationPartner1, String conversationPartner2,
                        List<MessageDto> lastMessage, Boolean deleted){
    @NonNull
    public String toString() {
        return String.format("Dialog: {id: %s, unreadCount: %s, conversationPartner1: %s, conversationPartner2: %s, lastMessage: %s, deleted: %s}",
                id(),unreadCount(),conversationPartner1(),conversationPartner2(),lastMessage(),deleted());
    }
}