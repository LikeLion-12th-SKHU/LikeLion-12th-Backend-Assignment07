package org.likelion.likelionrecrud.category.api.dto.request;

import java.util.List;

public record CategorySaveReqDto(
    List<Long> itemIds,
    String name
) {
}
