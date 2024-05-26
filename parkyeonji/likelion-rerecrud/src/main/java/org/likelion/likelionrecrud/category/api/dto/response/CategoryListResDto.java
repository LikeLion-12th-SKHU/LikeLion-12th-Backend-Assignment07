package org.likelion.likelionrecrud.category.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoryListResDto(
        List<CategoryInfoResDto> categories
) {
    public static CategoryListResDto from(List<CategoryInfoResDto> categories) {
        return CategoryListResDto.builder()
                .categories(categories)
                .build();
    }
}
