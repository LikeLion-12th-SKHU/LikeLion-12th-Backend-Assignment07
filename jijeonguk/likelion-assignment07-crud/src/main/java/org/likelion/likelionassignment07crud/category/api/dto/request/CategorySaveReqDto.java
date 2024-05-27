package org.likelion.likelionassignment07crud.category.api.dto.request;

public record CategorySaveReqDto(
        Long parentId,
        String name
) {
}