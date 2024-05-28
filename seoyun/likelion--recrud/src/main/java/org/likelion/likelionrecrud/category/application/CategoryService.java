package org.likelion.likelionrecrud.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.repository.CategoryRepository;
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

    @Transactional
    public void categoryUpdate(Long parent_Id, CategoryUpdateReqDto categoryUpdateReqDto) {
        Category category = categoryRepository.findById(parent_Id).orElseThrow
                (() -> new IllegalArgumentException("해당 카테고리가 없습니다. id=" + parent_Id));
        category.update(categoryUpdateReqDto);
    }

    public List<CategoryInfoResDto> categoryFindAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryInfoResDto::from)
                .collect(Collectors.toList());
    } //카테고리 모두 찾기..?

    public CategoryInfoResDto categoryFindOne(Long parent_Id) {
        Category category = categoryRepository.findById(parent_Id).orElseThrow
                (() -> new IllegalArgumentException("해당 카테고리가 없습니다. id=" + parent_Id));
        return CategoryInfoResDto.from(category);
    }// 카테고리 하나 찾기..?

    @Transactional
    public void categoryDelete(Long parent_Id) {
        Category category = categoryRepository.findById(parent_Id).orElseThrow
                (() -> new IllegalArgumentException("해당 카테고리가 없습니다. id=" + parent_Id));
        categoryRepository.delete(category);
    }
}

