package org.likelion.jangsu.category.domain.repository;

import org.likelion.jangsu.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Category, Long> {
}