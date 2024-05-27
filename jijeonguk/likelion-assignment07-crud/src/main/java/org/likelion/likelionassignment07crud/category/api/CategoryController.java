package org.likelion.likelionassignment07crud.category.api;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionassignment07crud.category.api.dto.request.CategorySaveReqDto;
import org.likelion.likelionassignment07crud.category.api.dto.request.CategoryUpdateReqDto;
import org.likelion.likelionassignment07crud.category.api.dto.response.CategoryInfoResDto;
import org.likelion.likelionassignment07crud.category.api.dto.response.CategoryListResDto;
import org.likelion.likelionassignment07crud.category.application.CategoryService;
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
        return new ResponseEntity<>("카테고리가 저장되었습니다.", HttpStatus.CREATED);
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
        categoryService.categoryUpdate(categoryId, categoryUpdateReqDto);
        return new ResponseEntity<>("카테고리가 수정되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.categoryDelete(categoryId);
        return ResponseEntity.ok("카테고리가 삭제되었습니다.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleError(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); //클라이언트 요청 에러

    }

}