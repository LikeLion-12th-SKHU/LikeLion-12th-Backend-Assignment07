package org.likelion.likelionassignment07crud.category.api.dto.request;

public record CategoryUpdateReqDto(
        Long parentId,
        String name
) {
}