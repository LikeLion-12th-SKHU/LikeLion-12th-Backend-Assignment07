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

    // 카테고리 저장
    @PostMapping
    public ResponseEntity<String> categorySave(@RequestBody CategorySaveReqDto categorySaveReqDto) {
        categoryService.categorySave(categorySaveReqDto);
        return new ResponseEntity<>("카테고리 저장!", HttpStatus.CREATED);
    }

    // 모든 카테고리 조회 findAll
    @GetMapping
    public ResponseEntity<CategoryListResDto> categoryFindAll() {
        CategoryListResDto categoryListResDto = categoryService.categoryFindAll();
        return new ResponseEntity<>(categoryListResDto, HttpStatus.OK);
    }

    // 특정 카테고리 조회 findOne
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryInfoResDto> categoryFindOne(@PathVariable("categoryId") Long categoryId) {
        CategoryInfoResDto categoryInfoResDto = categoryService.categoryFindOne(categoryId);
        return new ResponseEntity<>(categoryInfoResDto, HttpStatus.OK);
    }

    // 카테고리 수정
    @PatchMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryUpdateReqDto categoryUpdateReqDto) {
        categoryService.categoryUpdate(categoryId, categoryUpdateReqDto);
        return new ResponseEntity<>("카테고리가 수정되었습니다!", HttpStatus.OK);
    }

    // 카테고리 삭제
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.categoryDelete(categoryId);
        return ResponseEntity.ok("카테고리가 삭제되었습니다!");
    }

    // 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)   // 예외 처리를 위한 어노테이션, 처리하고자 하는 예외 클래스를 지정하고 설정한 응답 반환
    public ResponseEntity<String> handleError(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);   // bad_request: 클라이언트의 요청이 잘못된 형식이거나 부적절할 때

    }

}
