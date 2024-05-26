package org.likelion.likelionassignment07crud.item.domain.repository;

import org.likelion.likelionassignment07crud.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
