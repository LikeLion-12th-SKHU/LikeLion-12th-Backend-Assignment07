package org.likelion.jangsu.category.api;

import lombok.RequiredArgsConstructor;
import org.likelion.jangsu.category.api.dto.request.CatSaveReqDto;
import org.likelion.jangsu.category.api.dto.request.CatUpdateReqDto;
import org.likelion.jangsu.category.api.dto.response.CatInfoResDto;
import org.likelion.jangsu.category.api.dto.response.CatListResDto;
import org.likelion.jangsu.category.application.CatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CatController {

    private final CatService catService;

    @PostMapping
    public ResponseEntity<String> catSave(@RequestBody CatSaveReqDto categorySaveReqDto) {
        catService.catSave(categorySaveReqDto);
        return new ResponseEntity<>("아이템 저장!", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<CatInfoResDto> catFindOne(@PathVariable("catId") Long catId) {
        CatInfoResDto catInfoResDto = catService.catFindOne(catId);
        return new ResponseEntity<>(catInfoResDto, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<CatListResDto> catFindAll() {
        CatListResDto catListResDto = catService.catFindAll();
        return new ResponseEntity<>(catListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{catId}")
    public ResponseEntity<String> catUpdate(@PathVariable("catId") Long catId, @RequestBody CatUpdateReqDto catUpdateReqDto) {
        catService.catUpdate(catId, catUpdateReqDto);
        return new ResponseEntity<>("항목 수정!", HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<String> catDelete(@PathVariable("catId") Long catId) {
        catService.catDelete(catId);
        return new ResponseEntity<>("항목 수정!", HttpStatus.OK);
    }
}
