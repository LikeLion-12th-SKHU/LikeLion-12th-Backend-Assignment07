package org.likelion.jangsu.category.domain.repository;

import org.likelion.jangsu.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}