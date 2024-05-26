package org.likelion.likelionrecrud.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryListResDto;
import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.repository.CategoryRepository;
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
        Category parentCategory = null;
        if (categorySaveReqDto.parentId() != null) {
            parentCategory = categoryRepository.findById(categorySaveReqDto.parentId())
                    .orElseThrow(()-> new IllegalArgumentException("해당 카테고리가 없습니다."));
        }

        Category category = Category.builder()
                .parent(parentCategory)
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

        Category parent = null;
        if (categoryUpdateReqDto.parentId() != null) {
            parent = categoryRepository.findById(categoryUpdateReqDto.parentId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. Parent ID: " + categoryUpdateReqDto.parentId()));
        }
        category.update(parent, categoryUpdateReqDto.name());

//        Category parent = categoryRepository.findById(categoryUpdateReqDto.parentId())
//                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));
//        category.update(parent, categoryUpdateReqDto.name());
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));
        categoryRepository.delete(category);
    }

}
