package org.likelion.likelionrecrud.category.api.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import org.likelion.likelionrecrud.category.domain.Category;

import lombok.Builder;

@Builder
public record CategoryInfoDto(long categoryId, String categoryName, List<CategoryChildrenDto> children) {
	public static CategoryInfoDto from(Category category){
		return CategoryInfoDto.builder()
			.categoryId(category.getId())
			.categoryName(category.getName())
			.children(category.getChildren().stream().map(CategoryChildrenDto::from).collect(Collectors.toList()))
			.build();
	}
}
