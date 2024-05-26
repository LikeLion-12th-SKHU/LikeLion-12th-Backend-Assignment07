package org.likelion.likelionassignmentcrud.item.domain.repository;

import org.likelion.likelionassignmentcrud.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
