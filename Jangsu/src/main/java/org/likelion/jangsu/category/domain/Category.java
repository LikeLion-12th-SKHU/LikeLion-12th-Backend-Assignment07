package org.likelion.jangsu.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.jangsu.item.domain.CategoryItem;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    // 분류와 상품분류는 일대다 관계(이 클래스는 자식)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private CategoryType name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Category parent;

    @JsonIgnore
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @Builder
    public Category(Long id, CategoryType name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

    public void catUpdate(CategoryType name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

}
