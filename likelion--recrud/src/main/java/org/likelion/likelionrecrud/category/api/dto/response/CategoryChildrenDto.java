package org.likelion.likelionrecrud.category.api.dto.response;

import java.util.List;

import org.likelion.likelionrecrud.category.domain.Category;

import lombok.Builder;

@Builder
public record CategoryChildrenDto(Long id, String name) {
	public static CategoryChildrenDto from(Category category){
		return CategoryChildrenDto.builder()
			.id(category.getId())
			.name(category.getName())
			.build();
	}
}
