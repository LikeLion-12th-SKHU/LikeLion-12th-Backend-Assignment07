package org.likelion.jangsu.category.api;

import lombok.RequiredArgsConstructor;
import org.likelion.jangsu.category.api.dto.request.CategorySaveReqDto;
import org.likelion.jangsu.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.jangsu.category.api.dto.response.CategoryInfoResDto;
import org.likelion.jangsu.category.api.dto.response.CategoryListResDto;
import org.likelion.jangsu.category.application.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // 분류 저장
    @PostMapping
    public ResponseEntity<String> categorySave(@RequestBody CategorySaveReqDto categorySaveReqDto) {
        categoryService.catSave(categorySaveReqDto);
        return new ResponseEntity<>("분류 저장!", HttpStatus.CREATED);
    }

    // 단일 조회
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryInfoResDto> categoryFindOne(@PathVariable("catId") Long categoryId) {
        CategoryInfoResDto categoryInfoResDto = categoryService.catFindOne(categoryId);
        return new ResponseEntity<>(categoryInfoResDto, HttpStatus.OK);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<CategoryListResDto> categoryFindAll() {
        CategoryListResDto catListResDto = categoryService.catFindAll();
        return new ResponseEntity<>(catListResDto, HttpStatus.OK);
    }

    // 분류 업데이트
    @PatchMapping("/{catId}")
    public ResponseEntity<String> categoryUpdate(@PathVariable("catId") Long categoryId,
                                                 @RequestBody CategoryUpdateReqDto categoryUpdateReqDto) {
        categoryService.catUpdate(categoryId, categoryUpdateReqDto);
        return new ResponseEntity<>("분류 수정!", HttpStatus.OK);
    }

    // 분류 삭제
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> categoryDelete(@PathVariable("categoryId") Long categoryId) {
        categoryService.catDelete(categoryId);
        return new ResponseEntity<>("분류 삭제!", HttpStatus.OK);
    }
}
