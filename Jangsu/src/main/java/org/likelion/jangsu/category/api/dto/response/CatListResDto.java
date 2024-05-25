package org.likelion.jangsu.category.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CatListResDto(List<CatInfoResDto> categories) {
    public static CatListResDto from(List<CatInfoResDto> categories) {
        return CatListResDto.builder()
                .categories(categories)
                .build();
    }
}
