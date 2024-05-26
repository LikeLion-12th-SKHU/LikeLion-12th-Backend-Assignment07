package org.likelion.likelionrecrud.category.api.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record CategoryListDto(List<CategoryInfoDto> categories) {
	public static CategoryListDto from(List<CategoryInfoDto> categories){
		return CategoryListDto.builder()
			.categories(categories)
			.build();
	}

}
