package org.likelion.likelionrecrud.category.domain.repository;

import org.likelion.likelionrecrud.category.domain.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
}
