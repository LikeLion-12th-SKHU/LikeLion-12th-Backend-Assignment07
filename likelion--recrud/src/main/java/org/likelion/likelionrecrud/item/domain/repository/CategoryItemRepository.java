package org.likelion.likelionrecrud.item.domain.repository;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.likelion.likelionrecrud.category.domain.CategoryItem;
import org.likelion.likelionrecrud.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
	List<CategoryItem> findAllByItem(Item item);
}
