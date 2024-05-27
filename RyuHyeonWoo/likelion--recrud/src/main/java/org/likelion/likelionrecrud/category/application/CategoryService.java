package org.likelion.likelionrecrud.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoReqDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryListResDto;
import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
                    .orElseThrow(()-> new IllegalArgumentException("잘못된 id 요청"));
        }
        Category category = Category.builder()
                .parent(parentCategory)
                .name(categorySaveReqDto.name())
                .build();

        categoryRepository.save(category);
    }

    public CategoryListResDto categoryFindAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryInfoReqDto> categoryInfoReqDtoList = categories.stream()
                .map(CategoryInfoReqDto::from)
                .toList();

        return CategoryListResDto.from(categoryInfoReqDtoList);
    }

    public CategoryInfoReqDto categoryFindOne( Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new IllegalArgumentException("카테고리 없음"));

        return CategoryInfoReqDto.from(category);
    }

    @Transactional
    public void updateCategory(Long categoryId, CategoryUpdateReqDto categoryUpdateReqDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new IllegalArgumentException("해당 카테고리 없음"));

        Category parent = categoryRepository.findById(categoryUpdateReqDto.parentId())
                .orElseThrow(()-> new IllegalArgumentException("상위 카테고리 없음"));

        category.update(parent, categoryUpdateReqDto.name());
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new IllegalArgumentException("해당 카테고리 없음"));
        categoryRepository.delete(category);
    }

}
