package org.likelion.likelionassignmentrecrud.item.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentrecrud.item.api.dto.request.ItemUpdateReqDto;

import java.util.ArrayList;
import java.util.List;

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
}