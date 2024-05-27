package org.likelion.likelionrecrud.category.api.dto.request;

import lombok.Builder;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoReqDto;
import org.likelion.likelionrecrud.category.domain.Category;

public record CategoryUpdateReqDto(
        Long parentId,
        String name
) {

}
