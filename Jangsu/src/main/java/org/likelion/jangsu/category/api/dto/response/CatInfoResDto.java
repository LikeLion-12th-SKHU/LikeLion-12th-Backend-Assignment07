package org.likelion.jangsu.category.api.dto.response;

import lombok.Builder;
import org.likelion.jangsu.category.domain.CatType;
import org.likelion.jangsu.category.domain.Category;

@Builder
public record CatInfoResDto(CatType name) {
    public static CatInfoResDto from(Category category) {
        return CatInfoResDto.builder()
                .name(category.getName())
                .build();
    }
}
