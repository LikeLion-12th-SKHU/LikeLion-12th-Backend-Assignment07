package org.likelion.jangsu.item.application;

import lombok.RequiredArgsConstructor;
import org.likelion.jangsu.category.api.dto.response.CatInfoResDto;
import org.likelion.jangsu.item.api.dto.request.ItemSaveReqDto;
import org.likelion.jangsu.item.api.dto.request.ItemUpdateReqDto;
import org.likelion.jangsu.item.api.dto.response.ItemInfoResDto;
import org.likelion.jangsu.item.api.dto.response.ItemListResDto;
import org.likelion.jangsu.item.domain.CategoryItem;
import org.likelion.jangsu.item.domain.Item;
import org.likelion.jangsu.item.domain.repository.CatItemRepository;
import org.likelion.jangsu.item.domain.repository.ItemRepository;
import org.likelion.jangsu.order.api.dto.response.OrderInfoResDto;
import org.likelion.jangsu.order.api.dto.response.OrderItemResDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CatItemRepository catItemRepository;


    // 아이템 조회
    @Transactional
    public void itemSave(ItemSaveReqDto itemsaveReqDto) {
        Item item = Item.builder()
                .name(itemsaveReqDto.name())
                .price(itemsaveReqDto.price())
                .stockQuantity(itemsaveReqDto.stockQuantity())
                .build();
        itemRepository.save(item);
    }

    public List<CatInfoResDto> findAllByCategoryItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + itemId));

        List<CategoryItem> allByCategoryItem = catItemRepository.findAllByCategoryItem(item);

        List<CatInfoResDto> catInfoResDtoList = new ArrayList<>();
        for (CategoryItem categoryItem : allByCategoryItem) {
            CatInfoResDto c = new CatInfoResDto(
                    item.getName(),
                    categoryItem.getCategoryId(),
                    Collections.singletonList(new CatInfoResDto(
                            categoryItem.getCategoryId().getName()))
            );
            //생성된 OrderInfoResDto 객체를 orderInfoResDtoList에 추가
            catInfoResDtoList.add(c);
        }

        return catInfoResDtoList;
    }
    public ItemInfoResDto itemFindOne(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow
                (() -> new IllegalArgumentException("해당 상품이 없습니다."));
        return ItemInfoResDto.from(item);
    }

    public ItemListResDto itemFindAll() {
        List<Item> items = itemRepository.findAll();    // item 엔티티 목록을 itemRepository.findAll()을 통해 조회
        List<ItemInfoResDto> itemInfoResDtoList = items.stream()    //각 Item 객체를 ItemInfoResDto 객체로 변환
                .map(ItemInfoResDto::from)  // from 정적 메소드 사용
                .toList();  // MemberInfoResDto 객체들의 스트림을 리스트로 수집함
        return ItemListResDto.from(itemInfoResDtoList);
    }

    @Transactional
    public void updateItem(Long itemId, ItemUpdateReqDto itemUpdateReqDto) {
        Item item = itemRepository.findById(itemId).orElseThrow
                (() -> new IllegalArgumentException("해당 상품이 없습니다."));
        item.update(itemUpdateReqDto);
    }

    @Transactional
    public void itemDelete(Long itemId) {
        ItemInfoResDto itemInfoResDto = itemFindOne(itemId);
        Item item = Item.builder()
                .name(itemInfoResDto.name())
                .price(itemInfoResDto.price())
                .stockQuantity(itemInfoResDto.stockQuantity())
                .build();
        itemRepository.delete(item);
    }

}
