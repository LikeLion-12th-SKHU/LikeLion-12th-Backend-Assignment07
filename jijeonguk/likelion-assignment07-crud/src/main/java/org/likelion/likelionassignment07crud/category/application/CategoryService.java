package org.likelion.likelionassignment07crud.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionassignment07crud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionassignment07crud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionassignment07crud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionassignment07crud.category.api.dto.response.CategoryListResDto;
import org.likelion.likelionassignment07crud.category.domain.Category;
import org.likelion.likelionassignment07crud.category.domain.repository.CategoryRepository;
import org.likelion.likelionassignment07crud.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final ItemRepository itemRepository;
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
                .toList();
        return CategoryListResDto.from(categoryInfoResDtoList);
    }

    public CategoryInfoResDto categoryFindOne(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow
                (() -> new IllegalArgumentException("해당 카테고리가 없습니다. id = " + categoryId));
        return CategoryInfoResDto.from(category);
    }

    @Transactional
    public void categoryUpdate(Long categoryId, CategoryUpdateReqDto categoryUpdateReqDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. id = " + categoryId));

        Category parent = null;
        if (categoryUpdateReqDto.parentId() != null) {
            parent = categoryRepository.findById(categoryUpdateReqDto.parentId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 상위 카테고리가 없습니다. id = " + categoryId));
        }
        category.update(parent, categoryUpdateReqDto.name());
    }

    @Transactional
    public void categoryDelete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. id = " + categoryId));
        categoryRepository.delete(category);
    }
}
