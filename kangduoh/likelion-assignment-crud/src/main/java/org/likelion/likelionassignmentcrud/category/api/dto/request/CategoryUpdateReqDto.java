package org.likelion.likelionassignmentcrud.category.api.dto.request;

public record CategoryUpdateReqDto(
        Long parentId,
        String name
) {
}
