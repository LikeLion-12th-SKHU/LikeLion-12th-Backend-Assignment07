package org.likelion.likelionassignment07crud.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignment07crud.item.domain.Item;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_item_id")
    private Long id;

    // CategoryItem은 하나의 Category객체와 다대일,
    // 하나의 Item객체와도 다대일이라 ManyToOne관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // JsonIgnore은 특정 필드를 제외하고, Json형태로 변환시켜야 할 경우 사용한다.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public CategoryItem(Category category, Item item) {
        this.category = category;
        this.item = item;
    }
}