package org.likelion.likelionassignmentcrud.item.api.dto.request;

public record ItemSaveReqDto(
        String name,
        int price,
        int stockQuantity,
        Long categoryId
) {
}
