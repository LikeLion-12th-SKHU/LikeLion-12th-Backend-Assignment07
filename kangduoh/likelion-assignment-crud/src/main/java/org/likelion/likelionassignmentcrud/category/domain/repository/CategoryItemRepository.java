package org.likelion.likelionassignmentcrud.category.domain.repository;

import org.likelion.likelionassignmentcrud.category.domain.Category;
import org.likelion.likelionassignmentcrud.category.domain.CategoryItem;
import org.likelion.likelionassignmentcrud.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
    List<CategoryItem> findAllByItem(Item item);
    List<CategoryItem> findAllByCategory(Category category);
}
