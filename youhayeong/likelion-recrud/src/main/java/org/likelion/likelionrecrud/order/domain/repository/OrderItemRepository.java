package org.likelion.likelionrecrud.order.domain.repository;

import org.likelion.likelionrecrud.member.domain.Member;
import org.likelion.likelionrecrud.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// OrderItemRepository는 특정 회원의 주문 항목을 효율적으로 조회
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    //사용자 정의 JPQL 쿼리
    @Query(value = "select oi " +   // 모든 OrderItem 조회하는 쿼리
            "from OrderItem oi join fetch oi.order o " +    //OrderItem엔티티와 이를 포함하는 Order 엔티티를 패치 조인해서 특정 회원(Member)이 주문한 주문 항목 조회
            "where oi.order.member = :member")  // member는 메서드 파라미터로 전달된 Member 객체를 바인딩하는 위치 파라미터
    List<OrderItem> findAllByOrderMember(Member member);    // 특정 회원이 주문한 모든 주문 항목 조회
    // 조회 결과 레코드 수가 몇 개이든지 에러 발생 x, OrderItem findByOrderMember => 조회 결과 레코드 두 개 이상이면 오류 발생 o
}
