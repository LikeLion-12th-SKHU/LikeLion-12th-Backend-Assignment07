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

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    //@JsonIgnore
    //@ManyToOne(fetch = LAZY)
    //@JoinColumn(name = "parent_id")
    //private Category parentId;
    private String name;

    //@JsonIgnore
    //@OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Category> children = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    //@Builder
    //private Category(Category parentId, String name) {
    //    this.parentId = parentId;
    //    this.name = name;
    //}

    @Builder
    private Category(String name) {
        this.name = name;
    }


    //public void update(Category parentId, String name) {
    //    this.parentId = parentId;
    //    this.name = name;
    //}

    public void update(CategoryUpdateReqDto categoryUpdateReqDto) {
        this.name = categoryUpdateReqDto.name();
    }
}
