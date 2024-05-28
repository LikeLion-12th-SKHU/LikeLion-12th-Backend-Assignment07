package org.likelion.likelionrecrud.category.domain;
;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionrecrud.item.domain.Item;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 파라미터 x 디폴트 생성자

public class CategoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    private CategoryItem(Category category, Item item) {
        this.category = category;
        this.item = item;
    }

}
