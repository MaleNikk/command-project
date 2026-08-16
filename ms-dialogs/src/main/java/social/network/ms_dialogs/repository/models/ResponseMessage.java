package social.network.ms_dialogs.repository.models;

import org.springframework.lang.NonNull;

public record ResponseMessage(String type, String recipientId, ResponseData data) {

    @NonNull
    @Override
    public String toString() {
        return String.format("ResponseMessage: {type: %s, recipientId: %s, data: %s}",
                type(), recipientId(), data());
    }
}