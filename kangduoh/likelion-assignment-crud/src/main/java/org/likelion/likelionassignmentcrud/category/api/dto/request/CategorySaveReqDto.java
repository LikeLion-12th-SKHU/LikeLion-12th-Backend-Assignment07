package org.likelion.likelionassignmentcrud.category.api.dto.request;

public record CategorySaveReqDto(
        Long parentId,
        String name
) {
}
