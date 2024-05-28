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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;


    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryItem> category_itemList = new ArrayList<>();

    @Builder
    Category(String name) {
        this.name = name;
    }

    public void update(CategoryUpdateReqDto categoryUpdateReqDto) {
        this.name = categoryUpdateReqDto.name();
    }

}

