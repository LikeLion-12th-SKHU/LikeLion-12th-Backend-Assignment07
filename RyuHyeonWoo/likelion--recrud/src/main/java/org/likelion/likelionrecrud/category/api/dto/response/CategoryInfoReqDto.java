package org.likelion.likelionrecrud.category.api.dto.response;

import lombok.Builder;
import org.likelion.likelionrecrud.category.domain.Category;

@Builder
public record CategoryInfoReqDto(
        Long id,
        String name
) {
    public static CategoryInfoReqDto from(Category category) {
        return CategoryInfoReqDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
