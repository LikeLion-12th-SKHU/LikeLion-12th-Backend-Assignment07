package org.likelion.likelionrecrud.category.domain.repository;

import org.likelion.likelionrecrud.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
