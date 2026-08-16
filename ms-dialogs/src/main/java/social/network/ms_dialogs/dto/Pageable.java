package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

import java.util.List;

public record Pageable(Integer page, Integer size, List<?> sort) {
    @NonNull
    public String toString() {
        return String.format("Pageable:{page: %s, size: %s, sort: %s}",
                page(), size(), sort());
    }
}
