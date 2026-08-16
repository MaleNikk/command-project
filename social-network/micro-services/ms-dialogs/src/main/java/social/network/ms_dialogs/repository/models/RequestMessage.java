package social.network.ms_dialogs.repository.models;

import org.springframework.lang.NonNull;

public record RequestMessage(String type, String recipientId, RequestData data) {

    @NonNull
    @Override
    public String toString() {
        return String.format("MessageRequest:{ type: %s, recipientId: %s, data: %s}", type(), recipientId(), data());
    }
}