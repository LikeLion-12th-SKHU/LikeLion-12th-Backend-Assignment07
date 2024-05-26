package org.likelion.likelionassignmentcrud.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionassignmentcrud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionassignmentcrud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionassignmentcrud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionassignmentcrud.category.api.dto.response.CategoryListResDto;
import org.likelion.likelionassignmentcrud.category.domain.Category;
import org.likelion.likelionassignmentcrud.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void categorySave(CategorySaveReqDto categorySaveReqDto) {
        Category parent = null;
        if (categorySaveReqDto.parentId() != null) {
            parent = categoryRepository.findById(categorySaveReqDto.parentId())
                    .orElseThrow(() -> new IllegalArgumentException("상위 카테고리가 없습니다."));
        }

        Category category = Category.builder()
                .parent(parent)
                .name(categorySaveReqDto.name())
                .build();

        categoryRepository.save(category);
    }

    public CategoryListResDto categoryFindAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryInfoResDto> categoryInfoResDtoList = categories.stream()
                .map(CategoryInfoResDto::from)
                .toList();
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

        Category parent = categoryRepository.findById(categoryUpdateReqDto.parentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상위 카테고리가 없습니다."));

        category.updateCategory(categoryUpdateReqDto.name(), parent);
    }

    @Transactional
    public void categoryDelete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        categoryRepository.delete(category);
    }
}
