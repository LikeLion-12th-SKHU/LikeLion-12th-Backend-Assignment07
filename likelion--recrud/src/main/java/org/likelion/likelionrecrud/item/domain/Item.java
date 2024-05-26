package org.likelion.likelionrecrud.item.domain;

import java.util.ArrayList;
import java.util.List;

import org.likelion.likelionrecrud.category.api.dto.request.UpdateCategoryRequestDto;
import org.likelion.likelionrecrud.category.domain.Category;
import org.likelion.likelionrecrud.category.domain.CategoryItem;
import org.likelion.likelionrecrud.item.api.dto.request.ItemUpdateReqDto;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    // 이름, 가격, 재고수량(stockQuantity)을 가지고 있다. 상품을 주문하면 재고수량이 줄어든다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private Long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryItem> categoryItemList = new ArrayList<>();

    @Builder
    private Item(String name, int price, int stockQuantity, Long categoryId, String categoryName) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public void update(ItemUpdateReqDto itemUpdateReqDto){
        this.name = itemUpdateReqDto.name();
        this.price = itemUpdateReqDto.price();
        this.stockQuantity = itemUpdateReqDto.stockQuantity();
    }

    // public void updateCategoryInItem(UpdateCategoryInformationInItem requestDto)
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int resStock = this.stockQuantity - quantity;
        if (resStock <0){
            throw new IllegalArgumentException("없는듯");
        }
        this.stockQuantity = resStock;
    }
    public void updateCategoryInformation(Category category){
        this.categoryId = category.getId();
        this.categoryName = category.getName();
    }

}
