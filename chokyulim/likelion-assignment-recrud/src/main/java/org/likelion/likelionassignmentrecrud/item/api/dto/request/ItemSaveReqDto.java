package org.likelion.likelionassignmentrecrud.item.api.dto.request;

public record ItemSaveReqDto(
        String name,
        int price,
        int stockQuantity
) {
}
