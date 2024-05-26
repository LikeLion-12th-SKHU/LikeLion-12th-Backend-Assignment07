package org.likelion.jangsu.item.api;

import lombok.RequiredArgsConstructor;
import org.likelion.jangsu.item.api.dto.request.ItemSaveReqDto;
import org.likelion.jangsu.item.api.dto.request.ItemUpdateReqDto;
import org.likelion.jangsu.item.api.dto.response.ItemInfoResDto;
import org.likelion.jangsu.item.api.dto.response.ItemListResDto;
import org.likelion.jangsu.item.application.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<String> itemSave(@RequestBody ItemSaveReqDto itemSaveReqDto) {
        itemService.itemSave(itemSaveReqDto);
        return new ResponseEntity<>("아이템 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemInfoResDto> itemFindOne(@PathVariable("itemId") Long itemId) {
        ItemInfoResDto itemInfoResDto = itemService.itemFindOne(itemId);
        return new ResponseEntity<>(itemInfoResDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ItemListResDto> itemFindAll() {
        ItemListResDto itemListResDto = itemService.itemFindAll();
        return new ResponseEntity<>(itemListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<String> itemUpdate(@PathVariable("itemId") Long itemId, @RequestBody ItemUpdateReqDto itemUpdateReqDto) {
        itemService.updateItem(itemId, itemUpdateReqDto);
        return new ResponseEntity<>("아이템 수정!", HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> itemDelete(@PathVariable("itemId") Long itemId) {
        itemService.itemDelete(itemId);
        return new ResponseEntity<>("아이템 삭제!", HttpStatus.OK);
    }
}