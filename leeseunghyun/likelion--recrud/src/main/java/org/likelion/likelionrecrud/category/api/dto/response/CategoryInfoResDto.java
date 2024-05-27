package org.likelion.likelionrecrud.category.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.likelion.likelionrecrud.category.domain.Category;

@Getter
@AllArgsConstructor // 수정된 부분: @AllArgsConstructor 추가
public class CategoryInfoResDto {
    private Long id;
    private String name;

    public static CategoryInfoResDto from(Category category) { // 수정된 부분: from 메서드 추가
        return new CategoryInfoResDto(category.getId(), category.getName());
    }
}