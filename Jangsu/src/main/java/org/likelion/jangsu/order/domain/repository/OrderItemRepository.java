package org.likelion.jangsu.order.domain.repository;

import org.likelion.jangsu.member.domain.Member;
import org.likelion.jangsu.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // 사용자 정의 JPQL 쿼리 정의
    @Query(value = "select oi " +
            "from OrderItem oi join fetch oi.order o " +
            "where oi.order.member = :member ")
    List<OrderItem> findAllByOrderMember(Member member);
}
