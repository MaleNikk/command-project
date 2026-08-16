package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

public record SortObject(Boolean empty, Boolean sorted, Boolean unsorted) {
    @NonNull
    public String toString() {
        return String.format("SortObject:{empty: %s, sorted: %s, unsorted: %s}",
                empty(), sorted(), unsorted());
    }
}