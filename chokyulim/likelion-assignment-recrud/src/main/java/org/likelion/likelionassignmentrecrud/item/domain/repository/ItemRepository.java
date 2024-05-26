package org.likelion.likelionassignmentrecrud.item.domain.repository;

import org.likelion.likelionassignmentrecrud.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
