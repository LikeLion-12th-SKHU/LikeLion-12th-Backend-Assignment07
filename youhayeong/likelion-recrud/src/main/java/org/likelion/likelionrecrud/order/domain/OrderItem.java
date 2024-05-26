package org.likelion.likelionrecrud.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.likelion.likelionrecrud.item.domain.Item;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    //주문한 상품 정보와 주문 금액(orderPrice), 주문 수량(count) 정보를 가지고 있다.
    // orderItem 기준 item, order 모두 다대일 관계 => ManyToOne, JoinColumn o / OneToMany, mappedBy x

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id") // pk
    private Long id;

    @ManyToOne(fetch = LAZY)    // item 엔티티에 접근하는 순간에 데이터베이스에서 로딩 = 지연 로딩
    @JoinColumn(name = "item_id")   // fk
    private Item item;

    @JsonIgnore // json 변환 시 order 필드 생략 -> jsonignore가 있더라도 생성자에 포함 시킬 수 있음(order에서는 포함 x
    @ManyToOne(fetch = LAZY)    // eager 로딩인 single-valued association을 laze 로딩으로 설정?
    @JoinColumn(name = "order_id")  // fk
    private Order order;

    private int orderPrice;
    private int count;

    @Builder
    public OrderItem(Item item, Order order, int orderPrice, int count) {
        this.item = item;
        this.order = order;     // 주문 상품 목록에는 해당 주문이 필요함
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public void cancel() {  // 주문 취소하면 주문 수량만큼 item의 재고를 증가시키는 count 메소드 호출(나갔던 주문이 취소이기에 다시 원래대로(+)
        getItem().addStock(count);
    }
}
