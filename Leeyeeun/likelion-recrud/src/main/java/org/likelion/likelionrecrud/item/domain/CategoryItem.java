package org.likelion.likelionrecrud.item.domain;

import jakarta.persistence.*;
import lombok.Builder;
import org.likelion.likelionrecrud.category.domain.Category;

public class CategoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Builder
    public CategoryItem(Item item, Category parent) {
        this.item = item;
        this.parent = parent;
    }

}
