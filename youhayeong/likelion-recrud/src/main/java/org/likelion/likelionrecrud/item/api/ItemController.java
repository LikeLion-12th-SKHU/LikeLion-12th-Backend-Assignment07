package org.likelion.likelionrecrud.item.api;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.item.api.dto.request.ItemSaveReqDto;
//import org.likelion.likelionrecrud.item.api.dto.request.ItemUpdateReqDto;
import org.likelion.likelionrecrud.item.api.dto.request.ItemUpdateReqDto;
import org.likelion.likelionrecrud.item.api.dto.response.ItemInfoResDto;
import org.likelion.likelionrecrud.item.api.dto.response.ItemListResDto;
import org.likelion.likelionrecrud.item.application.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    // 상품 저장
    @PostMapping
    public ResponseEntity<String> itemSave(@RequestBody ItemSaveReqDto itemSaveReqDto) {
        itemService.itemSave(itemSaveReqDto);
        return new ResponseEntity<>("아이템 저장!", HttpStatus.CREATED);
    }

    // 모든 상품 조회
    @GetMapping
    public ResponseEntity<ItemListResDto> itemFindAll() {
        ItemListResDto itemListResDto = itemService.itemFindAll();
        return new ResponseEntity<>(itemListResDto, HttpStatus.OK); // 상품 목록 리턴 = requestbody 필요 x
    }

    // 특정 상품 조회
    @GetMapping("/{itemId}")
    public ResponseEntity<ItemInfoResDto> itemFindOne(@PathVariable("itemId") Long itemId) {
        ItemInfoResDto itemInfoResDto = itemService.itemFindOne(itemId);
        return new ResponseEntity<>(itemInfoResDto, HttpStatus.OK); // 상품 정보 리턴 = requestbody 필요 x
    }

    // 상품 수정
    @PatchMapping("/{itemId}")  // 서버에 전달하려는 데이터 필요 => requestbody 필요
    public ResponseEntity<String> itemUpdate(@PathVariable("itemId") Long itemId, @RequestBody ItemUpdateReqDto itemUpdateReqDto) {
        itemService.updateItem(itemId, itemUpdateReqDto);
        return new ResponseEntity<>("아이템 수정!", HttpStatus.OK);
    }

    // 상품 삭제
    @DeleteMapping("/{itemId}")  // 서버에 전달하려는 데이터 필요 => requestbody 필요
    public ResponseEntity<String> itemDelete(@PathVariable("itemId") Long itemId) {
        itemService.itemDelete(itemId);
        return new ResponseEntity<>("아이템 삭제!", HttpStatus.OK);
    }
}
