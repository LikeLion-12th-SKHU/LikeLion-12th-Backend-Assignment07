package org.likelion.likelionrecrud.category.api;

import org.likelion.likelionrecrud.category.api.dto.request.SaveCategoryRequestDto;
import org.likelion.likelionrecrud.category.api.dto.request.UpdateCategoryRequestDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryInfoDto;
import org.likelion.likelionrecrud.category.api.dto.response.CategoryListDto;
import org.likelion.likelionrecrud.category.application.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping("")
	public ResponseEntity<CategoryInfoDto> saveCategory(@RequestBody SaveCategoryRequestDto categoryRequestDto){
		return new ResponseEntity<>(categoryService.createCategory(categoryRequestDto), HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<CategoryListDto> getAllCategories(){
		return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
	}

	@PatchMapping("/{categoryId}")
	public ResponseEntity<String> updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody UpdateCategoryRequestDto requestDto){
		categoryService.updateCategory(categoryId, requestDto);
		return new ResponseEntity<>("수정 완료",HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable(value = "categoryId")Long categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>("삭제완료", HttpStatus.NO_CONTENT);
	}


}
