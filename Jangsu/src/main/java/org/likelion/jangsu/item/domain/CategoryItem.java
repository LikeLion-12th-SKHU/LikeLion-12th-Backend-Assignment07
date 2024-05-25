package org.likelion.jangsu.item.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.jangsu.category.domain.Category;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryItem {
    //주문한 상품 정보와 주문 금액(orderPrice), 주문 수량(count) 정보를 가지고 있다.

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parentId")
    private Category categoryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}