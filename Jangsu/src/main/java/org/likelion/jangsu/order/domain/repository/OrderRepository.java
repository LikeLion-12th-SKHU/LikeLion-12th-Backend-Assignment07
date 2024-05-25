package org.likelion.jangsu.order.domain.repository;

import org.likelion.jangsu.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}