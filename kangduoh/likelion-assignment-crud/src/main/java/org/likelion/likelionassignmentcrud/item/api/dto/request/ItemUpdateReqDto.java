package org.likelion.likelionassignmentcrud.item.api.dto.request;

public record ItemUpdateReqDto(
        String name,
        int price,
        int stockQuantity,
        Long categoryId
) {
}
