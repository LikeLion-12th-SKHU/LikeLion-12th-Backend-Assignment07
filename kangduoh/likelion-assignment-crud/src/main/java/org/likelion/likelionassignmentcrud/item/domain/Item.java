package org.likelion.likelionassignmentcrud.item.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.category.domain.Category;
import org.likelion.likelionassignmentcrud.category.domain.CategoryItem;
import org.likelion.likelionassignmentcrud.item.api.dto.request.ItemUpdateReqDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryItem> categories = new ArrayList<>();

    @Builder
    private Item(String name, int price, int stockQuantity, List<CategoryItem> categories) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categories = categories;
    }

    public void update(ItemUpdateReqDto itemUpdateReqDto) {
        this.name = itemUpdateReqDto.name();
        this.price = itemUpdateReqDto.price();
        this.stockQuantity = itemUpdateReqDto.stockQuantity();
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int resStock = this.stockQuantity - quantity;

        if (resStock < 0) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stockQuantity = resStock;
    }

    public List<String> getCategoryNames() {
        return categories.stream()
                .map(CategoryItem::getCategory)
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}
