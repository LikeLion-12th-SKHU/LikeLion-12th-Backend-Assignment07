package org.likelion.likelionrecrud.item.api.dto.response;

import lombok.Builder;

import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.item.domain.Item;

@Builder
public record ItemInfoResDto(
        String name,
        int price,
        int stockQuantity,
        Long categoryId,
        String categoryName

) {
    public static ItemInfoResDto from(Item item) {
        return ItemInfoResDto.builder()
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .categoryId(item.getCategoryId())
                .categoryName(item.getCategoryName())
                .build();
    }
}
