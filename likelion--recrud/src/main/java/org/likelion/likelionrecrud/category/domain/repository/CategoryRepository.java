package org.likelion.likelionrecrud.category.domain.repository;

import java.util.List;

import org.likelion.likelionrecrud.category.domain.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Override
	@EntityGraph(attributePaths = {"children"})
	List<Category> findAll();
}
