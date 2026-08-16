package social.network.ms_gateway.configuration.websocket.dto;

import org.springframework.lang.NonNull;

import java.util.Date;

public record MessageResponseWebsocket (
        String type,
        String recipientId,
        ResponseData data
) {

    public record ResponseData (
            Date date,
            String conversationPartner1,
            String conversationPartner2,
            String messageText,
            TypeMessageWebsocket readStatus,
            String dialogId,
            String id) {

        @NonNull
        @Override
        public String toString() {
            return String.format("""
                    ResponseData:
                                {
                                date: %s,
                                conversationPartner1: %s,
                                conversationPartner2: %s,
                                messageText: %s,
                                readStatus: %s,
                                dialogId: %s,
                                id: %s}
                    """, date(), conversationPartner1(),conversationPartner2(),messageText(),readStatus(),dialogId(),id());
        }
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(
                """
                ResponseMessage:
                                {
                                 type: %s,
                                 recipientId: %s,
                                 data: %s
                                 }
                """,type(),recipientId(),data());
    }
}