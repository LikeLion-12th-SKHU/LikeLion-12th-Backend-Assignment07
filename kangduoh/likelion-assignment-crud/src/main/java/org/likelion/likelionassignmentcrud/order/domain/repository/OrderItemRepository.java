package org.likelion.likelionassignmentcrud.order.domain.repository;

import org.likelion.likelionassignmentcrud.member.domain.Member;
import org.likelion.likelionassignmentcrud.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 특정 회원 주문 항목 조회
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // 사용자 정의 JPQL 쿼리
    @Query(value = "select oi " +
            "from OrderItem oi join fetch oi.order o " +
            "where oi.order.member = :member")
    List<OrderItem> findAllByOrderMember(Member member);
}
