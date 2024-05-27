package org.likelion.likelionrecrud.category.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoryListResDto(
        List<CategoryInfoReqDto> categorys
) {
    public static CategoryListResDto from(List<CategoryInfoReqDto> categorys) {
        return CategoryListResDto.builder()
                .categorys(categorys)
                .build();
    }
}
