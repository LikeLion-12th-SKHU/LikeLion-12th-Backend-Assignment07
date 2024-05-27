package org.likelion.likelionassignmentcrud.category.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.category.domain.Category;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record CategoryInfoResDto(
        Long id,
        String name,
        Long parentId,
        List<SubCategoryResDto> subCategories
) {
    public static CategoryInfoResDto from(Category category) {
        Long parentId = category.getParent() != null ? category.getParent().getId() : null;
        return CategoryInfoResDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(parentId)
                .subCategories(category.getSubCategories().stream().map(SubCategoryResDto::from).collect(Collectors.toList()))
                .build();
    }
}
