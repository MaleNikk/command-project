package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

public record RequestEditMessage(String messageId, String cause, String authorId) {

    @NonNull
    @Override
    public String toString() {
        return String.format("RequestEditMessage: {messageId: %s, cause: %s, authorId: %s}",
                messageId(), cause(), authorId());
    }
}

