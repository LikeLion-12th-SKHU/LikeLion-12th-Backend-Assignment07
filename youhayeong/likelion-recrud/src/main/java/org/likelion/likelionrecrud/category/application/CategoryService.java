package org.likelion.likelionrecrud.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryListResDto;
import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.repository.CategoryRepository;
import org.likelion.likelionrecrud.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    // 카테고리 저장
    @Transactional
    public void categorySave(CategorySaveReqDto categorySaveReqDto) {
        Category category = Category.builder()
                .name(categorySaveReqDto.name())
                .build();
        categoryRepository.save(category);
    }

    // 모든 카테고리 조회
    public CategoryListResDto categoryFindAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryInfoResDto> categoryInfoResDtoList = categories.stream()
                .map(CategoryInfoResDto::from)
                .toList();
        return CategoryListResDto.from(categoryInfoResDtoList);
    }

    // 특정 카테고리 조회
    public CategoryInfoResDto categoryFindOne(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow
                (() -> new IllegalArgumentException("해당 카테고리가 없습니다. id = " + categoryId));
        return CategoryInfoResDto.from(category);
    }

    // 카테고리 업데이트
    @Transactional
    public void categoryUpdate(Long categoryId, CategoryUpdateReqDto categoryUpdateReqDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow
                (() -> new IllegalArgumentException("해당하는 카테고리가 없습니다. id =" + categoryId));
        Category parent = categoryRepository.findById(categoryUpdateReqDto.parentId()).orElseThrow
                (() -> new IllegalArgumentException("해당하는 부모 카테고리가 없습니다. id = " + categoryId));
        category.update(categoryUpdateReqDto.name(), parent);
    }

    // 카테고리 삭제
    @Transactional
    public void categoryDelete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. id = " + categoryId));
        categoryRepository.delete(category);
    }
}
