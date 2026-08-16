package social.network.ms_dialogs.service.dialogs;

import social.network.ms_dialogs.dto.PageResponseDto;
import social.network.ms_dialogs.dto.PageableObject;
import social.network.ms_dialogs.dto.SortObject;

import java.util.List;

public class PageableBuilder<T> {
    private static final int PAGE_SIZE = 8;

    public PageResponseDto<T> getPageable(List<T> content, int page, String sort) {
        int totalSize = content.size();
        int countPage = totalSize /PAGE_SIZE;
        double divisionPage = (double) totalSize / (double)PAGE_SIZE;
        int totalPage = totalSize < PAGE_SIZE ? 1 : (divisionPage == (double) 0.0F ? countPage : countPage + 1);
        boolean isFirst = page == 1 || page == 0;
        boolean isLast = page == totalPage;
        int offset = isFirst ? 0 : (page - 1) * PAGE_SIZE;
        return new PageResponseDto<>(
                totalPage,
                (long) totalSize,
                PAGE_SIZE,
                content.subList(offset, totalPage == 1 ? totalSize : (isFirst ? PAGE_SIZE : (isLast ? totalSize : (page - 1) * PAGE_SIZE))),
                page,
                getSort(totalSize),
                new PageableObject(
                        offset,
                        getSort(totalSize),
                        totalPage > 1,
                        page,
                        PAGE_SIZE,
                        totalSize < PAGE_SIZE),
                page == 1,
                page == totalPage,
                totalSize,
                totalSize == 0);
    }

    private static SortObject getSort(int totalSize) {
        return new SortObject(totalSize == 0, totalSize > 1, totalSize <= 1);
    }
}
