package org.likelion.likelionrecrud.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    // 카테고리와 카테고리_item은 일대다 관계
    // 카테고리는 parent_id(자기참조 외래키), name 가지고 있음
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk
    @Column(name = "category_id")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // 자기 참조 fk
    private Category parent;

    private String name;

    @Builder
    private Category(String name, Category Parent) {
        this.name = name;
        this.parent = parent;
    }

    // 카테고리 이름, 카테고리 parent update
    public void update(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
}
