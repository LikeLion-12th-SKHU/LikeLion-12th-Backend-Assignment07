package org.likelion.likelionrecrud.category.api.dto.request;

public record CategorySaveReqDto(
        Long parentId,
        String name
) {
}
