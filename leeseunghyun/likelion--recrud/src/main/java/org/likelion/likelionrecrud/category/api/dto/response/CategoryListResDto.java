package org.likelion.likelionrecrud.category.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoryListResDto(
        List<CategoryInfoResDto> categorys
) {
    public static CategoryListResDto from(List<CategoryInfoResDto> categorys){
        return CategoryListResDto.builder()
                .categorys(categorys)
                .build();
    }
}
