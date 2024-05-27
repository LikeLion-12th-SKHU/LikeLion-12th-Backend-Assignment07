package org.likelion.likelionrecrud.category.domain.CategoryRepository;

import org.likelion.likelionrecrud.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
