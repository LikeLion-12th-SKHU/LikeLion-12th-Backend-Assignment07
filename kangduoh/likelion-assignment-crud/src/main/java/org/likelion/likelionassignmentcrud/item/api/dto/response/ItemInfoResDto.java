package org.likelion.likelionassignmentcrud.item.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.item.domain.Item;

import java.util.List;

@Builder
public record ItemInfoResDto(
        String name,
        int price,
        int stockQuantity,
        List<String> categoryNames
) {
    public static ItemInfoResDto from(Item item) {
        List<String> categoryNames = item.getCategoryNames();

        return ItemInfoResDto.builder()
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .categoryNames(categoryNames)
                .build();
    }
}
