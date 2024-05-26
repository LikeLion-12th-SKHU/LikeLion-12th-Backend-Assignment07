package org.likelion.jangsu.category.api.dto.response;

import lombok.Builder;
import org.likelion.jangsu.category.domain.CategoryType;
import org.likelion.jangsu.category.domain.Category;

@Builder
public record CategoryInfoResDto(CategoryType name, Long id) {
    public static CategoryInfoResDto from(Category category) {
        return CategoryInfoResDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
