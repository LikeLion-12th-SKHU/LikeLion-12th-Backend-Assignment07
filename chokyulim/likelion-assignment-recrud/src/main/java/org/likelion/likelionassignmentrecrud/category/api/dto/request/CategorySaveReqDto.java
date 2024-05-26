package org.likelion.likelionassignmentrecrud.category.api.dto.request;

import lombok.Builder;
import org.likelion.likelionassignmentrecrud.category.domain.Category;

public record CategorySaveReqDto(
        Long parentId,
        String name
) {
}
