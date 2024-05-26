package org.likelion.likelionassignmentcrud.category.domain.repository;

import org.likelion.likelionassignmentcrud.category.domain.CategoryItem;
import org.likelion.likelionassignmentcrud.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
    @Query("SELECT ci " +
            "FROM CategoryItem ci " +
            "JOIN FETCH ci.category " +
            "JOIN FETCH ci.item i " +
            "WHERE ci.item = :item")
    List<CategoryItem> findByItem(Item item);
}
