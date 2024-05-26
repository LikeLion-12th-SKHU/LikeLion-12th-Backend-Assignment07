package org.likelion.likelionrecrud.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.likelion.likelionrecrud.member.domain.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 기본 생성자를 자동으로 생성
@Table(name = "orders")
public class Order {
    // 주문과 주문상품은 일대다 관계,
    // 주문은 상품을 주문한 회원과 주문리스트, 주문 날짜, 주문 상태(status)를 가지고 있다.
    // 주문 상태는 열거형을 사용해서 주문(ORDER), 취소(CANCEL)을 표현할 수 있다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키
    @Column(name = "order_id")
    private Long id;

    @ManyToOne  // order : member (다 : 일, 한 회원은 여러 개의 주문을 할 수 있음)
    @JoinColumn(name = "member_id") // fk
    private Member member;  // 주문 레코드를 조회해서 Order 엔티티 객체가 생성될 때 회원 정보도 member 속성에 자동으로 채워짐(eager) <- 관련 속성이 언제 채워지는 지 기준으로 eager | lazy

    @JsonIgnore // 직렬화 및 역질렬화 시 이 필드를 포함 시키지 않음, "orderitems": [] (orderItems 필드)생략됨/ 주문이 생성되면 해당 주문에 대한 상품 목록이 나중에 추가됨
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // order 일, orderItem 다(한 주문에는 여러개의 상품이 포함 될 수 있음/ order 테이블 기준 orderItem이 '다' 이기에 mappedBy 적어줌
    private List<OrderItem> orderItems = new ArrayList<>();     // OrderItem 객체가 생성될 때, 소속 주문 목록도 orderItems 속성에 자동으로 채워짐

    private LocalDateTime orderDate;    // 날짜와 시간 함께 표현하는 객체

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    private Order(Member member, OrderStatus status) { // orderItems(order 객체를 생성한 후 주문 상품을 추가하는 방식이므로 생성 시에는 해당 필드를 초기화 시킬 필요 x, id 제외한 나머지 필드
        this.member = member;
        this.status = status;
        this.orderDate = LocalDateTime.now();
    }

    public void cancel() {  // 주문 취소 메소드 cancel
        this.status = OrderStatus.CANCEL;       // 주문 상태를 OrderStatus이 CANCEL로 변경 => 주문 취소
        orderItems.forEach(OrderItem::cancel);  // orderitem 리스트의 각 orderItem 객체에 대해 addOrderItem 메소드 호출
        // 각 주문 아이템을 현재 주문 객체의 주문 아이템 목록에 추가(addOrderItem
    }
}
