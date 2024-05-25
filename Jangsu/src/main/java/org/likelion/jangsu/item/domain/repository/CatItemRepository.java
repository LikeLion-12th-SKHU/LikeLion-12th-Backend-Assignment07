package org.likelion.jangsu.item.domain.repository;

import org.likelion.jangsu.item.domain.CategoryItem;
import org.likelion.jangsu.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatItemRepository extends JpaRepository<CategoryItem, Long> {
    // 모든 CategoryItem 조회하는 쿼리
    @Query(value = "select ci" +
            "from CategoryItem ci join fetch ci.category c" +
            "where ci.category.item = :item")
    List<CategoryItem> findAllByCategoryItem(Item item);
}
