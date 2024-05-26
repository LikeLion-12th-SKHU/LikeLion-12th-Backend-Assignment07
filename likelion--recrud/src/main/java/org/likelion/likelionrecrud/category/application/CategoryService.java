package org.likelion.likelionrecrud.category.application;

import java.util.List;
import java.util.stream.Collectors;

import org.likelion.likelionrecrud.category.api.dto.request.SaveCategoryRequestDto;
import org.likelion.likelionrecrud.category.api.dto.request.UpdateCategoryRequestDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryListDto;
import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Transactional
	public CategoryInfoDto createCategory(SaveCategoryRequestDto requestDto) {
		Category parentCategory = (requestDto.parentId() != null) ?
			categoryRepository.findById(requestDto.parentId())
				.orElseThrow(() -> new IllegalArgumentException("잘못된 id 요청입니다.")) : null;
		Category category = Category.builder()
			.name(requestDto.categoryName())
			.parent(parentCategory)
			.build();
		categoryRepository.save(category);
		return CategoryInfoDto.from(category);
	}

	public CategoryListDto getAllCategory() {
		return CategoryListDto.from(
			categoryRepository.findAll()
				.stream()
				.map(CategoryInfoDto::from)
				.collect(Collectors.toList()));
	}

	@Transactional
	public void updateCategory(Long categoryId, UpdateCategoryRequestDto requestDto) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(() -> new IllegalArgumentException("id없는듯"));
		Category parent = (requestDto.parentId() != null) ?
			categoryRepository.findById(requestDto.parentId())
				.orElseThrow(() -> new IllegalArgumentException("부모카테고리 id없는듯")) : null;
		category.updateCategory(requestDto.name(), parent);
	}

	@Transactional
	public void deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 카테고리."));
		category.getCategoryItems().forEach(
			categoryItem -> categoryItem.updateCategoryAndItem(null)
		);
		categoryRepository.delete(category);
	}
}
