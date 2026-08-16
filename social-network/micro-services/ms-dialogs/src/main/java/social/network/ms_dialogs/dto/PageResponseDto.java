package social.network.ms_dialogs.dto;

import org.springframework.lang.NonNull;

import java.util.List;

public record PageResponseDto<T>(Integer totalPage, Long totalElements, Integer size, List<T> content, Integer number,
                                 SortObject sort, PageableObject pageable, Boolean first, Boolean last,
                                 Integer numberOfElements, Boolean empty) {
    @NonNull
    public String toString() {
        return String.format("Data: { totalPage: %s, totalElements: %s, size: %s, content: %s, number: %s, sort: %s, pageable: %s, first: %s, last: %s, numberOfElements: %s, empty: %s}",
                totalPage(), totalElements(), size(), content(), number(), sort(), pageable(), first(), last(), numberOfElements(), empty());
    }
}