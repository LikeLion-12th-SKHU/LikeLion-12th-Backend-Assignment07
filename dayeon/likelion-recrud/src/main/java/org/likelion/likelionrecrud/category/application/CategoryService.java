package org.likelion.likelionrecrud.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryItemResDto;
import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.CategoryItem;
import org.likelion.likelionrecrud.category.domain.repository.CategoryItemRepository;
import org.likelion.likelionrecrud.category.domain.repository.CategoryRepository;
import org.likelion.likelionrecrud.item.domain.Item;
import org.likelion.likelionrecrud.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final CategoryItemRepository categoryItemRepository;

    @Transactional
    public void categorySave(CategorySaveReqDto categorySaveReqDto) {
        Category category = Category.builder()
                .name(categorySaveReqDto.name())
                .build();
        categoryRepository.save(category);

        for (int i = 0; i <categorySaveReqDto.itemIds().size(); i++) {
            Long itemId = categorySaveReqDto.itemIds().get(i);

            Item item = itemRepository.findById(itemId).orElseThrow(
                    () -> new IllegalArgumentException("해당 상품이 없습니다. id=" + itemId)
            );

            categoryItemRepository.save(CategoryItem.builder()
                    .item(item)
                    .category(category)
                    .build());

        }
    }

    public List<CategoryInfoResDto> findCategoryInfoByItemId(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 없습니다. id="+itemId));

        List<CategoryItem> allByCategoryItem = categoryItemRepository.findAllByItem(item);

        List<CategoryInfoResDto> categoryInfoResDtoList = new ArrayList<>();

        for (CategoryItem categoryItem : allByCategoryItem) {
            CategoryInfoResDto c = new CategoryInfoResDto(
                    item.getId(),
                    categoryItem.getCategory().getId(),
                    new CategoryItemResDto(
                            categoryItem.getItem().getId(),
                            categoryItem.getCategory().getId())
            );
            categoryInfoResDtoList.add(c);
        }
        return categoryInfoResDtoList;
    }

    @Transactional
    public void updateCategory(Long categoryId, CategoryUpdateReqDto categoryUpdateReqDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리가 없습니다.")
        );
        category.update(category.getParent(), categoryUpdateReqDto.name());
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리가 없습니다.")
        );
        categoryRepository.delete(category);
    }
}
