package social.network.ms_gateway.configuration.websocket.dto;

import org.springframework.lang.NonNull;

public record MessageRequestWebsocket (
    String type,
    String recipientId,
    RequestData data
) {
        public record RequestData(
                String time,
                String conversationPartner1,
                String conversationPartner2,
                String messageText,
                TypeMessageWebsocket readStatus,
                String id
        ) {
            @NonNull
            @Override
            public String toString() {
                return String.format("""
                    MessageData :
                                 {
                                 time: %s,
                                 conversationPartner1: %s,
                                 conversationPartner2: %s,
                                 messageText: %s,
                                 readStatus: %s,
                                 id: %s
                                 }
                    """, time(), conversationPartner1(), conversationPartner2(), messageText(), readStatus(), id());
            }
        }

        @NonNull
        @Override
        public String toString() {
            return String.format(
                    """
                            MessageRequest:
                                          {
                                           type: %s,
                                           recipientId: %s,
                                           data: %s
                                           }
                            """, type(), recipientId(), data());
        }
    }