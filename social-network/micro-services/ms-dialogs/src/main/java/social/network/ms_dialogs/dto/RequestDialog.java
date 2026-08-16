package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

public record RequestDialog(String partnerId1, String partnerId2, String message) {

    @NonNull
    @Override
    public String toString() {
        return String.format("RequestDialog: {partnerId1: %s, partnerId2: %s, message: %s}",
                partnerId1(), partnerId2(), message());
    }
}