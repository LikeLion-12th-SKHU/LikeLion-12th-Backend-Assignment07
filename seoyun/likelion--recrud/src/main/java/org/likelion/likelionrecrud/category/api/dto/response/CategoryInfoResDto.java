package org.likelion.likelionrecrud.category.api.dto.response;


import lombok.Builder;
import org.likelion.likelionrecrud.category.domain.Category;


@Builder
public record CategoryInfoResDto(
        String name) {
    public static CategoryInfoResDto from(Category category){
        return CategoryInfoResDto.builder()
                .name(category.getName())
                .build();
    }
}
