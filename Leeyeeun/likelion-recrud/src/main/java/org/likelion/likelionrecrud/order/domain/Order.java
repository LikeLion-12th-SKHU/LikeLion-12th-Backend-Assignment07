package org.likelion.likelionrecrud.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.likelion.likelionrecrud.member.domain.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void cancel(){
        this.status = OrderStatus.CANCEL;
        orderItems.forEach(OrderItem::cancel);
    }

}
