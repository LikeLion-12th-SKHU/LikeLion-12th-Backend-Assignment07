package org.likelion.jangsu.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.jangsu.member.domain.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 파라미터 x 디폴트 생성자
@Table(name = "orders") // order가 예약어(키워드)라 오류가 발생하기 때문에 테이블 이름을 바꿔둠.
public class Order {
    // 주문과 주문상품은 일대다 관계

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키
    @Column(name = "order_id")  //속성명
    private Long id;

    @ManyToOne //다대일
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // 연관관계 주입
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

    // 주문 취소
    public void cancel() {
        this.status = OrderStatus.CANCEL;
        orderItems.forEach(OrderItem::cancel);
    }
}

