package org.likelion.likelionassignmentrecrud.item.api.dto.request;

public record ItemUpdateReqDto(
        String name,
        int price,
        int stockQuantity
) {
}
