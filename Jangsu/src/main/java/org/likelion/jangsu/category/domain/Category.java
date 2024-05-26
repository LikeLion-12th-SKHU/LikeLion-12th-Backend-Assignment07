package org.likelion.jangsu.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.jangsu.category.api.dto.request.CatUpdateReqDto;
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
    private Long categoryId;

    @Enumerated(value = EnumType.STRING)
    private CatType name;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryId",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryItem> parentId = new ArrayList<>();

    @Builder
    private Category(CatType name) {
        this.name = name;
    }

    public void catUpdate(CatUpdateReqDto CatUpdateReqDto) {
        this.name = CatUpdateReqDto.name();
    }

}
