package org.likelion.likelionrecrud.category.api.dto.response;

import lombok.Builder;
import java.util.List;

@Builder
public record CategoryListResDto(
        List<CategoryInfoResDto> categories
) {
    // 주어진 categories를 사용하여 CategoryListResDto 객체를 빌드하는 정적 메소드
    public static CategoryListResDto from(List<CategoryInfoResDto> categories) {
        return CategoryListResDto.builder()
                .categories(categories)
                .build();
    }
}
