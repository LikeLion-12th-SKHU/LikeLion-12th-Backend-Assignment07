package org.likelion.likelionrecrud.category.api.dto;


import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryListResDto;
import org.likelion.likelionrecrud.category.application.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryListResDto> categoryFindAll() {
        List<CategoryInfoResDto> categories = categoryService.categoryFindAll();
        CategoryListResDto categoryListResDto = CategoryListResDto.from(categories);
        return new ResponseEntity<>(categoryListResDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> categorySave(@RequestBody CategorySaveReqDto categorySaveReqDto) {
        categoryService.categorySave(categorySaveReqDto);
        return new ResponseEntity<>("카테고리 저장!", HttpStatus.CREATED);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<String> categoryUpdate(@PathVariable("categoryId") Long categoryId,
                                               @RequestBody CategoryUpdateReqDto categoryUpdateReqDto) {
        categoryService.categoryUpdate(categoryId, categoryUpdateReqDto);
        return new ResponseEntity<>("카테고리 수정!", HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> categoryDelete(@PathVariable("categoryId") Long categoryId) {
        categoryService.categoryDelete(categoryId);
        return new ResponseEntity<>("카테고리 삭제!", HttpStatus.OK);
    }


}