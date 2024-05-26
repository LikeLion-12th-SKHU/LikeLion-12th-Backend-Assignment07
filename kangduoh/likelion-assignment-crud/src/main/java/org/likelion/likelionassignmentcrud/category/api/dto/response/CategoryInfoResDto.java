package org.likelion.likelionassignmentcrud.category.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.category.domain.Category;

@Builder
public record CategoryInfoResDto(
        Long id,
        String name,
        Long parentId
) {
    public static CategoryInfoResDto from(Category category) {
        return CategoryInfoResDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent().getId())
                .build();
    }
}
