package org.likelion.likelionrecrud.category.domain.repository;

import org.likelion.likelionrecrud.category.domain.CategoryItem;
import org.likelion.likelionrecrud.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
    /*@Query(value = "select ci" +
            "from CaterogyItem ci join fetch ci.category c " +
            "where ci.category.item = :item ")
    List<CategoryItem> findAllByCategoryItem(Item item);*/
    List<CategoryItem> findAllByItem(Item item);
}
