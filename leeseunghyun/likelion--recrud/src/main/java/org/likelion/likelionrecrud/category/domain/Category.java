package org.likelion.likelionrecrud.category.domain;


import jakarta.persistence.*;
import lombok.*;
import org.likelion.likelionrecrud.category.api.dto.request.CategoryUpdateReqDto;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    private String name;

    @Builder
    public Category(String name) {
        this.name = name;
    }

    //@ManyToOne
    //@JoinColumn(name = "parent_id")
    //private Category parent;

    //@OneToMany(mappedBy = "parent")
    //private List<Category> subcategories;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems;

    //@Builder
    //public Category(String name, Category parent) {
    //this.name = name;
    //this.parent = parent;
    //}

    public void update(String name) {
        this.name = name;
    }
}



