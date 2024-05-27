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

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> categorySave(@RequestBody CategorySaveReqDto categorySaveReqDto) {
        categoryService.categorySave(categorySaveReqDto);
        return new ResponseEntity<>("카테고리 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<CategoryListResDto> getCategoryByItem(@PathVariable("itemId") Long itemId) {
        List<CategoryInfoResDto> categoryInfoResDto = categoryService.findCategoryInfoByItemId(itemId);
        return ResponseEntity.ok(CategoryListResDto.from(categoryInfoResDto));
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryUpdateReqDto categoryUpdateReqDto) {
        categoryService.updateCategory(categoryId, categoryUpdateReqDto);
        return ResponseEntity.ok("카테고리가 업데이트되었습니다!");
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("카테고리가 삭제되었습니다!");
    }
}
