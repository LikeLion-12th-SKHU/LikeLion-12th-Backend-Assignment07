package org.likelion.likelionassignment07crud.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignment07crud.member.domain.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {
    // 주문과 주문상품은 일대다 관계,
    // 주문은 상품을 주문한 회원과 주문리스트, 주문 날짜, 주문 상태(status)를 가지고 있다.
    // 주문 상태는 열거형을 사용해서 주문(ORDER), 취소(CANCEL)을 표현할 수 있다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    private Order(Member member, OrderStatus status) {
        this.member = member;
        this.status = status;
        this.orderDate = LocalDateTime.now();
    }
    public void cancel() {
        this.status = OrderStatus.CANCEL;
        orderItems.forEach(OrderItem::cancel);
    }
}
