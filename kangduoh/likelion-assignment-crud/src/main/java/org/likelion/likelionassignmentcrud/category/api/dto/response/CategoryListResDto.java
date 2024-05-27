package org.likelion.likelionassignmentcrud.category.api.dto.response;

import java.util.List;

public record CategoryListResDto(
        List<CategoryInfoResDto> categories
) {
    public static CategoryListResDto from(List<CategoryInfoResDto> categories) {
        return new CategoryListResDto(categories);
    }
}
