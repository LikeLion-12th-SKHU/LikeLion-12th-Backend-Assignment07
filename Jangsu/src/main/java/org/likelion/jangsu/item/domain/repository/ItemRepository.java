package org.likelion.jangsu.item.domain.repository;

import org.likelion.jangsu.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}