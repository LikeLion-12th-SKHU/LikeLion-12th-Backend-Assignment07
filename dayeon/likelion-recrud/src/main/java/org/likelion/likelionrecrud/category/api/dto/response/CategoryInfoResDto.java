package org.likelion.likelionrecrud.category.api.dto.response;

public record CategoryInfoResDto(
        Long itemId,
        Long categoryId,
        CategoryItemResDto categoryItems
) {

}
