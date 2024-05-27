package org.likelion.likelionrecrud.item.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.item.api.dto.request.ItemSaveReqDto;
import org.likelion.likelionrecrud.item.api.dto.request.ItemUpdateReqDto;
import org.likelion.likelionrecrud.item.api.dto.response.ItemInfoResDto;
import org.likelion.likelionrecrud.item.api.dto.response.ItemListResDto;
import org.likelion.likelionrecrud.item.domain.Item;
import org.likelion.likelionrecrud.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void itemSave(ItemSaveReqDto itemsaveReqDto) {
        Item item = Item.builder()  // 상품 생성: item 엔티티 생성하고 itemRepository를 통해 저장
                .name(itemsaveReqDto.name())    // 상품 이름
                .price(itemsaveReqDto.price())  // 상품 가격
                .stockQuantity(itemsaveReqDto.stockQuantity())  // 상품 재고
                .build();
        itemRepository.save(item); // 상품 저장
    }

    public ItemListResDto itemFindAll() {   // 모든 상품 조회 메소드 itemFinaAll
        List<Item> items = itemRepository.findAll();    // item 엔티티 목록을 itemRepository.findAll()을 통해 조회
        List<ItemInfoResDto> itemInfoResDtoList = items.stream()    // 각 item 객체를 itemInfoResDto로 객체로 변환 후 리스트로
                .map(ItemInfoResDto::from)
                .toList();
        return ItemListResDto.from(itemInfoResDtoList);     // itemInfoResDtoList로부터 itemInfoResDto 생성 후 리턴
    }

    public ItemInfoResDto itemFindOne(Long itemId) {    // 특정 상품 조회 메소드 itemFindOne
        Item item = itemRepository.findById(itemId).orElseThrow
                (() -> new IllegalArgumentException("해당 상품이 없습니다."));
        return ItemInfoResDto.from(item);
    }

    @Transactional
    public void updateItem(Long itemId, ItemUpdateReqDto itemUpdateReqDto) {    // itemid로 조회하여 특정 상품 수정 메소드 updateItem
        Item item = itemRepository.findById(itemId).orElseThrow
                (() -> new IllegalArgumentException("해당 상품이 없습니다."));
        item.update(itemUpdateReqDto);

    }

    @Transactional
    public void itemDelete(Long itemId) {   // itemId로 조회하여 특정 상품 삭제 메소드 itemDelete
        Item item = itemRepository.findById(itemId).orElseThrow
                (()-> new IllegalArgumentException("해당 상품이 없습니다."));
        itemRepository.delete(item);
    }
}