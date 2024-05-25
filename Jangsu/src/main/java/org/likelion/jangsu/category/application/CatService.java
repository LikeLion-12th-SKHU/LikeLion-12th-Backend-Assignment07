package org.likelion.jangsu.category.application;

import lombok.RequiredArgsConstructor;
import org.likelion.jangsu.category.api.dto.request.CatSaveReqDto;
import org.likelion.jangsu.category.api.dto.request.CatUpdateReqDto;
import org.likelion.jangsu.category.api.dto.response.CatInfoResDto;
import org.likelion.jangsu.category.api.dto.response.CatListResDto;
import org.likelion.jangsu.category.domain.Category;
import org.likelion.jangsu.category.domain.repository.CatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    @Transactional
    public void catSave(CatSaveReqDto catSaveReqDto) {
        Category category = Category.builder()
                .name(catSaveReqDto.name()).build();
        catRepository.save(category);
    }

    public CatInfoResDto catFindOne(Long catId) {
        Category category = catRepository.findById(catId)
                .orElseThrow(() -> new IllegalArgumentException("해당 분류는 없습니다."));
        return CatInfoResDto.from(category);
    }

    public CatListResDto catFindAll() {
        List<Category> categories = catRepository.findAll();
        List<CatInfoResDto> itemInfoResDtoList = categories.stream()
                .map(CatInfoResDto::from)  // from 정적 메소드 사용
                .toList();
        return CatListResDto.from(itemInfoResDtoList);
    }

    @Transactional
    public void catUpdate(Long catId, CatUpdateReqDto CatUpdateReqDto) {
        Category category = catRepository.findById(catId).orElseThrow
                (() -> new IllegalArgumentException("해당 분류는 없습니다."));
        category.catUpdate(CatUpdateReqDto);
    }

    @Transactional
    public void catDelete(Long catId) {
        CatInfoResDto catInfoResDto = catFindOne(catId);
        Category category = Category.builder()
                .name(catInfoResDto.name())
                .build();
        catRepository.delete(category);
    }

}
