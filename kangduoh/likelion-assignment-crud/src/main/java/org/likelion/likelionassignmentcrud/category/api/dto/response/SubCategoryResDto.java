package org.likelion.likelionassignmentcrud.category.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.category.domain.Category;

@Builder
public record SubCategoryResDto(
        Long id,
        String name
) {
    public static SubCategoryResDto from(Category category) {
        return SubCategoryResDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
