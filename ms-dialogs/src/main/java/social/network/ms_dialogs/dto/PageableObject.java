package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

public record PageableObject(Integer offset, SortObject sort, Boolean paged, Integer pageNumber,
                             Integer pageSize, Boolean unpaged) {
    @NonNull
    public String toString() {
        return String.format("PageableObject: {offset: %s, sort: %s, paged: %s, pageNumber: %s, pageSize: %s, unpaged: %s}",
                offset(), sort(), paged(), pageNumber(), pageSize(), unpaged());
    }
}