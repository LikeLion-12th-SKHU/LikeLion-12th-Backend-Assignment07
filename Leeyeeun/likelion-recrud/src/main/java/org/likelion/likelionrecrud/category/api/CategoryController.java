package org.likelion.likelionrecrud.category.api;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryListResDto;
import org.likelion.likelionrecrud.category.application.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> categorySave(@RequestBody CategorySaveReqDto categorySaveReqDto) {
        categoryService.categorySave(categorySaveReqDto);
        return new ResponseEntity<>("카테고리 저장 !", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CategoryListResDto> categoryFindAll() {
        CategoryListResDto categoryListResDto = categoryService.categoryFindAll();
        return new ResponseEntity<>(categoryListResDto, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryInfoResDto> categoryFindOne(@PathVariable("categoryId") Long categoryId) {
        CategoryInfoResDto categoryInfoResDto = categoryService.categoryFindOne(categoryId);
        return new ResponseEntity<>(categoryInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryUpdateReqDto categoryUpdateReqDto) {
        categoryService.updateCategory(categoryId, categoryUpdateReqDto);
        return new ResponseEntity<>("카테고리 수정 !", HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("카테고리 삭제 !", HttpStatus.OK);
    }
}

