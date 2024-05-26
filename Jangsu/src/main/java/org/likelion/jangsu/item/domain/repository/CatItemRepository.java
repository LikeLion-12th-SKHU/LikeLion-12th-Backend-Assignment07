package org.likelion.jangsu.item.domain.repository;

import org.likelion.jangsu.item.domain.CategoryItem;
import org.likelion.jangsu.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatItemRepository extends JpaRepository<CategoryItem, Long> {
    List<CategoryItem> findAllByItem(Item item);
}
