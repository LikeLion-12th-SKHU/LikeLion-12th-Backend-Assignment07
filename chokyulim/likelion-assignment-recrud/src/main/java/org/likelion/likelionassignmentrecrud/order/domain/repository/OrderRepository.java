package org.likelion.likelionassignmentrecrud.order.domain.repository;

import org.likelion.likelionassignmentrecrud.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberId(Long memberId);
}
