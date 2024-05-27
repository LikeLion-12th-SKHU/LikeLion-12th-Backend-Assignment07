package org.likelion.likelionassignment07crud.category.domain.repository;

import org.likelion.likelionassignment07crud.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
