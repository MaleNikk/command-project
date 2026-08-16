package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

public record UnreadCountDto(long count) {
    @NonNull
    public String toString() {
        return String.format("UnreadCountDto: {count: %s}", count());
    }
}