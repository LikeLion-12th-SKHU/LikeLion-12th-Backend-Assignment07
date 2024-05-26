package org.likelion.likelionrecrud.item.application;

import lombok.RequiredArgsConstructor;

import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.CategoryItem;
import org.likelion.likelionrecrud.category.domain.repository.CategoryRepository;
import org.likelion.likelionrecrud.item.api.dto.request.ItemSaveReqDto;
import org.likelion.likelionrecrud.item.api.dto.request.ItemUpdateReqDto;
import org.likelion.likelionrecrud.item.api.dto.response.ItemInfoResDto;
import org.likelion.likelionrecrud.item.api.dto.response.ItemListResDto;
import org.likelion.likelionrecrud.item.domain.Item;
import org.likelion.likelionrecrud.item.domain.repository.CategoryItemRepository;
import org.likelion.likelionrecrud.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryItemRepository categoryItemRepository;

    @Transactional
    public void itemSave(ItemSaveReqDto itemsaveReqDto) {
        Category category = categoryRepository.findById(itemsaveReqDto.categoryId())
            .orElseThrow(()-> new IllegalArgumentException("해당 카테고리 찾기 실패"));
        Item item = Item.builder()
                .name(itemsaveReqDto.name())
                .price(itemsaveReqDto.price())
                .stockQuantity(itemsaveReqDto.stockQuantity())
                .categoryName(category.getName())
                .categoryId(category.getId())
                .build();
        itemRepository.save(item);

        CategoryItem categoryItem = CategoryItem.builder()
            .category(category)
            .item(item)
            .build();
        categoryItemRepository.save(categoryItem);
    }

    public ItemListResDto itemFindAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemInfoResDto> itemInfoResDtoList = items.stream()
                .map(ItemInfoResDto::from)
                .toList();
        return ItemListResDto.from(itemInfoResDtoList);
    }

    public ItemInfoResDto itemFindOne(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow
                (() -> new IllegalArgumentException("해당 상품이 없습니다."));
        return ItemInfoResDto.from(item);
    }

    @Transactional
    public ItemInfoResDto updateItem(Long itemId, ItemUpdateReqDto updateReqDto){
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("없음ㅋ"));
        Category category = updateReqDto.categoryId()!=null?
            categoryRepository.findById(updateReqDto.categoryId()).orElseThrow(()->new IllegalArgumentException("너가 찾는 카테고리 없음")):
            null; //그 카테고리 분류를 제외시키고 싶을 수 있으니 null로
         //Item의 카테고리를 변경하고싶다는거니까.
        item.update(updateReqDto); //내용에 대한것만 업데이트 시키는 경우가 더 많을테니 내용 업데이트하고 연관관계를 아랫부분으로 넘겨.
        if(category!=null) {
            List<CategoryItem> categoryItemList = categoryItemRepository.findAllByItem(item);
            if (categoryItemList.isEmpty()){ //비어있으면 추가하고싶다는 것.
                CategoryItem newCategoryItem = CategoryItem.builder()
                    .item(item)
                    .category(category)
                    .build();
                categoryItemRepository.save(newCategoryItem); //새로운 관계 생성
                newCategoryItem.updateCategoryAndItem(category);
                return ItemInfoResDto.from(item); //밑에 안들리게 리턴해버려.
            } //아이템이 변경되어 기존 아이템을 가지고있던 categoryitem들의 itemid도 다 바꿔주는 상황. 같은 애들도 다 업데이트하면 더티체킹해서 같은애 안변하겠지 ㅇㅈ
            categoryItemRepository.findAllByItem(item).forEach(categoryItem -> categoryItem.updateCategoryAndItem(category));
        }
        return ItemInfoResDto.from(item);
    }

    @Transactional
    public void itemDelete(Long itemId){
        Item item = itemRepository.findById(itemId).orElseThrow(
            () -> new IllegalArgumentException("없는듯ㅋ")
        );
        itemRepository.delete(item);
    }

}