package org.likelion.jangsu.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.jangsu.category.api.dto.request.CategorySaveReqDto;
import org.likelion.jangsu.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.jangsu.category.api.dto.response.CategoryInfoResDto;
import org.likelion.jangsu.category.api.dto.response.CategoryListResDto;
import org.likelion.jangsu.category.domain.Category;
import org.likelion.jangsu.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void catSave(CategorySaveReqDto categorySaveReqDto) {
        Category parentCategory = (categorySaveReqDto.parent()) != null ?
                categoryRepository.findById(categorySaveReqDto.parent())
                        .orElseThrow(() -> new IllegalArgumentException("해당 분류는 없습니다.")) : null;

        Category category = Category.builder()
                .name(categorySaveReqDto.name())
                .parent(parentCategory).build();
        categoryRepository.save(category);
    }

    public CategoryInfoResDto catFindOne(Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new IllegalArgumentException("해당 분류는 없습니다."));
        return CategoryInfoResDto.from(category);
    }

    public CategoryListResDto catFindAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryInfoResDto> itemInfoResDtoList = categories.stream()
                .map(CategoryInfoResDto::from)
                .toList();
        return CategoryListResDto.from(itemInfoResDtoList);
    }

    @Transactional
    public void catUpdate(Long catId, CategoryUpdateReqDto categoryUpdateReqDto) {
        Category category = categoryRepository.findById(catId).orElseThrow
                (() -> new IllegalArgumentException("해당 분류는 없습니다."));

        Category parent = categoryRepository.findById(categoryUpdateReqDto.parent())
                        .orElseThrow(() -> new IllegalArgumentException("해당 분류는 없습니다. ID :" + categoryUpdateReqDto.parent()));

        category.catUpdate(categoryUpdateReqDto.name(), parent);
    }

    @Transactional
    public void catDelete(Long catId) {
        CategoryInfoResDto categoryInfoResDto = catFindOne(catId);
        Category category = Category.builder()
                .name(categoryInfoResDto.name())
                .build();
        categoryRepository.delete(category);
    }

}
