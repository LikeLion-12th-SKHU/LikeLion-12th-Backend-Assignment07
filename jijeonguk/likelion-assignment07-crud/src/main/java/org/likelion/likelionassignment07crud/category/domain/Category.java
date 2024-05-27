package org.likelion.likelionassignment07crud.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    // 카테고리하고 카테고리 아이템은 일대다 관계
    // 자기 참조 하는 방법 ()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // 부모 카테고리에 대한 외래키
    private Category parent;

    private String name;

    // orphanRemoval = true 부모 엔티티와 관계가 끊어진 고아 엔티티 자동으로 삭제
    // 자식 카테코리들과 일대다 관계
    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> childCategories = new ArrayList<>();

    // 카테고리 아이템들과 일대다 관계
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @Builder
    public Category(Category parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public void update(Category parent, String name) {
        this.parent = parent;
        this.name = name;
    }
}