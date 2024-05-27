package org.likelion.likelionrecrud.category.api.dto.request;

import lombok.Builder;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoReqDto;
import org.likelion.likelionrecrud.category.domain.Category;

@Builder
public record CategoryUpdateReqDto(
        Long parentId,
        String name
) {

}
