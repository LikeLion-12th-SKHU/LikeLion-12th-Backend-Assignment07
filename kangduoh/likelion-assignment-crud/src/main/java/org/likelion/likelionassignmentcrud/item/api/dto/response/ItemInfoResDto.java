package org.likelion.likelionassignmentcrud.item.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.item.domain.Item;

@Builder
public record ItemInfoResDto(
        String name,
        int price,
        int stockQuantity,
        String categoryName
) {
    public static ItemInfoResDto from(Item item) {
        return ItemInfoResDto.builder()
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .categoryName(item.getCategoryName())
                .build();
    }
}
