package org.likelion.likelionrecrud.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryListResDto;
import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.CategoryRepository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void categorySave(CategorySaveReqDto categorySaveReqDto) {
        Category category = Category.builder()
                .name(categorySaveReqDto.name())
                .build();
        categoryRepository.save(category);
    }

    public CategoryListResDto categoryFindAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryInfoResDto> categoryInfoResDtoList = categories.stream()
                .map(CategoryInfoResDto::from)
                .collect(Collectors.toList());
        return CategoryListResDto.from(categoryInfoResDtoList);
    }

    public CategoryInfoResDto categoryFindOne(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));
        return CategoryInfoResDto.from(category);
    }

    @Transactional
    public void updateCategory(Long categoryId, CategoryUpdateReqDto categoryUpdateReqDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));
        category.update(categoryUpdateReqDto.name());
    }

    @Transactional
    public void categoryDelete(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("해당 카테고리가 없습니다.");
        }
        categoryRepository.deleteById(categoryId);
    }
}
