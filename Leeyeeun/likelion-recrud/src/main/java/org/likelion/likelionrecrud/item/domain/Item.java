package org.likelion.likelionrecrud.item.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionrecrud.item.api.dto.request.ItemUpdateDto;

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

    @Builder
    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void update(ItemUpdateDto itemUpdateDto) {
        this.name=itemUpdateDto.name();
        this.price=itemUpdateDto.price();
        this.stockQuantity=itemUpdateDto.stockQuantity();
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int resStock = this.stockQuantity - quantity;
        if (resStock < 0 ) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stockQuantity = resStock;
    }
}
