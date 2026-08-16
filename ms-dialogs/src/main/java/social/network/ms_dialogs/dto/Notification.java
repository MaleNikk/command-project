package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

public record Notification(String id, String serviceType, String time, String data) {
    @NonNull
    public String toString() {
        return String.format("Notification: {id: %s, serviceType: %s, time: %s, data: %s}",
                id(), serviceType(), time(), data());
    }
}