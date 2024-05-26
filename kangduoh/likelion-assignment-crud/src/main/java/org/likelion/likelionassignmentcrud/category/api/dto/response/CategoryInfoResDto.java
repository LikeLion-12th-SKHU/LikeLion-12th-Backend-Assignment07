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
        Long parentId = category.getParent() != null ? category.getParent().getId() : null;
        return CategoryInfoResDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(parentId)
                .build();
    }
}
