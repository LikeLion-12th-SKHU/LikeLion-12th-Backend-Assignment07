package org.likelion.likelionassignmentrecrud.order.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionassignmentrecrud.item.domain.Item;
import org.likelion.likelionassignmentrecrud.item.domain.repository.ItemRepository;
import org.likelion.likelionassignmentrecrud.member.domain.Member;
import org.likelion.likelionassignmentrecrud.member.domain.repository.MemberRepository;
import org.likelion.likelionassignmentrecrud.order.api.dto.request.OrderSaveReqDto;
import org.likelion.likelionassignmentrecrud.order.api.dto.response.OrderInfoResDto;
import org.likelion.likelionassignmentrecrud.order.api.dto.response.OrderItemResDto;
import org.likelion.likelionassignmentrecrud.order.domain.Order;
import org.likelion.likelionassignmentrecrud.order.domain.OrderItem;
import org.likelion.likelionassignmentrecrud.order.domain.OrderStatus;
import org.likelion.likelionassignmentrecrud.order.domain.repository.OrderItemRepository;
import org.likelion.likelionassignmentrecrud.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void orderSave(OrderSaveReqDto orderSaveReqDto) {
        // 회원 id 조회
        Member member = memberRepository.findById(orderSaveReqDto.memberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id= " + orderSaveReqDto.memberId()));

        // 주문 생성
        Order order = Order.builder()
                .member(member)
                .orderStatus(OrderStatus.ORDER)
                .build();

        orderRepository.save(order);

        // 주문 상품 목록: dto에서 상품 id 목록, 각 상품의 주문 수량을 이용
        for (int i = 0; i < orderSaveReqDto.itemIds().size(); i++) {
            Long itemId = orderSaveReqDto.itemIds().get(i);
            int count = orderSaveReqDto.counts().get(i);

            // 상품 조회
            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id= " + itemId));

            // 주문 상품 생성, 저장
            orderItemRepository.save(OrderItem.builder()
                    .item(item)
                    .order(order)
                    .count(count)
                    .orderPrice(item.getPrice())
                    .build());
            item.removeStock(count); // 상품 재고 수정(-1)
        }
    }
    public List<OrderInfoResDto> findOrderInfoByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id= " + memberId));
        List<OrderItem> allByOrderMember = orderItemRepository.findAllByOrderMember(member);
        // 반환할 OrderInfoResDto 객체들을 담을 리스트 초기화
        List<OrderInfoResDto> orderInfoResDtoList = new ArrayList<>();
        for(OrderItem orderItem : allByOrderMember) {
            OrderInfoResDto o = new OrderInfoResDto(
                    member.getId(),
                    orderItem.getOrder().getId(),
                    new OrderItemResDto(
                            orderItem.getItem().getId(),
                            orderItem.getItem().getPrice(),
                            orderItem.getCount()
                    ));
                    // 생성된 OrderInfoResDto 객체를 리스트
            orderInfoResDtoList.add(o);
        }
        return orderInfoResDtoList;
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. id= " + orderId));
        order.cancel();
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId) // 컨트롤 알트 v - 자동 완성: 먼지 모르게쓤!
            .orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. id= " + orderId));
        orderRepository.delete(order);
    }
}
