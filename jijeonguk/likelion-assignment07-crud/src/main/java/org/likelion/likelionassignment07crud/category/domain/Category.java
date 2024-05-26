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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

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