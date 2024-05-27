package org.likelion.likelionassignment07crud.category.api.dto.response;
import lombok.Builder;
import org.likelion.likelionassignment07crud.category.domain.Category;

@Builder
public record CategoryInfoResDto(
        Long id,
        String name

) {
    public static CategoryInfoResDto from(Category category) {
        return CategoryInfoResDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
