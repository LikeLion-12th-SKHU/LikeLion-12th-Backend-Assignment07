package org.likelion.likelionassignmentcrud.item.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionassignmentcrud.category.domain.Category;
import org.likelion.likelionassignmentcrud.category.domain.CategoryItem;
import org.likelion.likelionassignmentcrud.category.domain.repository.CategoryItemRepository;
import org.likelion.likelionassignmentcrud.category.domain.repository.CategoryRepository;
import org.likelion.likelionassignmentcrud.item.api.dto.request.ItemSaveReqDto;
import org.likelion.likelionassignmentcrud.item.api.dto.request.ItemUpdateReqDto;
import org.likelion.likelionassignmentcrud.item.api.dto.response.ItemInfoResDto;
import org.likelion.likelionassignmentcrud.item.api.dto.response.ItemListResDto;
import org.likelion.likelionassignmentcrud.item.domain.Item;
import org.likelion.likelionassignmentcrud.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryItemRepository categoryItemRepository;

    @Transactional
    public void itemSave(ItemSaveReqDto itemsaveReqDto) {
        Category category = categoryRepository.findById(itemsaveReqDto.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        Item item = Item.builder()
                .name(itemsaveReqDto.name())
                .price(itemsaveReqDto.price())
                .stockQuantity(itemsaveReqDto.stockQuantity())
                .build();

        itemRepository.save(item);

        CategoryItem categoryItem = CategoryItem.builder()
                .category(category)
                .item(item)
                .build();

        categoryItemRepository.save(categoryItem);
    }

    // 데이터베이스에 저장된 모든 아이템을 조회하고, 이를 DTO 리스트로 변환하여 반환
    public ItemListResDto itemFindAll() {
        List<Item> items = itemRepository.findAll();    // item 엔티티 목록을 itemRepository.findAll()을 통해 조회
        List<ItemInfoResDto> itemInfoResDtoList = items.stream()    //각 Item 객체를 ItemInfoResDto 객체로 변환
                .map(ItemInfoResDto::from)  // from 정적 메소드 사용
                .toList();  // MemberInfoResDto 객체들의 스트림을 리스트로 수집함
        return ItemListResDto.from(itemInfoResDtoList);
    }

    // 특정 아이템을 ID로 조회하고, 이를 DTO로 변환하여 반환
    public ItemInfoResDto itemFindOne(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow
                (() -> new IllegalArgumentException("해당 상품이 없습니다."));
        return ItemInfoResDto.from(item);
    }

    @Transactional
    public void updateItem(Long itemId, ItemUpdateReqDto itemUpdateReqDto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));

        item.update(itemUpdateReqDto);

        List<CategoryItem> categoryItems = categoryItemRepository.findByItem(item);
        categoryItemRepository.deleteAll(categoryItems);

        Category category = categoryRepository.findById(itemUpdateReqDto.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        CategoryItem newCategoryItem = CategoryItem.builder()
                .category(category)
                .item(item)
                .build();

        categoryItemRepository.save(newCategoryItem);
    }

    @Transactional
    public void itemDelete(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));

        List<CategoryItem> categoryItems = categoryItemRepository.findByItem(item);
        categoryItemRepository.deleteAll(categoryItems);

        itemRepository.delete(item);
    }
}