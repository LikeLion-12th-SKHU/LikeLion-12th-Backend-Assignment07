package org.likelion.jangsu.item.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.jangsu.category.domain.Category;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryItem {
    // 상품분류와 분류는 다대일 관계(이 클래스는 부모)
    // 상품분류와 상품은 다대일 관계

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}