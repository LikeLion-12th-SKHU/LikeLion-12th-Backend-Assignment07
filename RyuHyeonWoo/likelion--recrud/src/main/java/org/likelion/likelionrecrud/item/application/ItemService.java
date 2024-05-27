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
        Item item = Item.builder()
                .name(itemsaveReqDto.name())
                .price(itemsaveReqDto.price())
                .stockQuantity(itemsaveReqDto.stockQuantity())
                .build();
        itemRepository.save(item);
    }

    public ItemListResDto itemFindAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemInfoResDto> itemInfoResDtoList = items.stream()
                .map(ItemInfoResDto::from)
                .toList();
        return ItemListResDto.from(itemInfoResDtoList);
    }

    public ItemInfoResDto itemFindOne(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow
                (() -> new IllegalArgumentException("해당 상품이 없습니다."));
        return ItemInfoResDto.from(item);
    }

    @Transactional
    public void updateItem(Long itemId, ItemUpdateReqDto itemUpdateReqDto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품 없음."));
        item.update(itemUpdateReqDto);
    }

    @Transactional
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품 없음"));
        itemRepository.delete(item);
    }
}