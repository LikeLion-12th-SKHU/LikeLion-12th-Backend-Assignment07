package org.likelion.likelionassignmentrecrud.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentrecrud.member.domain.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Orders") // 테이블 이름 지정
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    // 주문 - 상품을 주문한 회원과 주문리스트, 주문날짜, 주문상태
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore // 해당 필드 포함X
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    private Order(Member member, OrderStatus orderStatus) {
        this.member = member;
        this.status = orderStatus;
        this.orderDate = LocalDateTime.now();
    }

    public void cancel() {
        this.status = OrderStatus.CANCEL;
        orderItems.forEach(OrderItem::cancel);
    }
}
