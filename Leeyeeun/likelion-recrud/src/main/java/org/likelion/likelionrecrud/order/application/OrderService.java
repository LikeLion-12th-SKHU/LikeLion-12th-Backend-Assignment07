package org.likelion.likelionrecrud.order.application;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.item.domain.Item;
import org.likelion.likelionrecrud.item.domain.repository.ItemRepository;
import org.likelion.likelionrecrud.member.domain.Member;
import org.likelion.likelionrecrud.member.domain.repository.MemberRepository;
import org.likelion.likelionrecrud.order.api.dto.request.OrderSaveReqDto;
import org.likelion.likelionrecrud.order.api.dto.response.OrderInfoResDto;
import org.likelion.likelionrecrud.order.api.dto.response.OrderItemResDto;
import org.likelion.likelionrecrud.order.domain.Order;
import org.likelion.likelionrecrud.order.domain.OrderItem;
import org.likelion.likelionrecrud.order.domain.OrderStatus;
import org.likelion.likelionrecrud.order.domain.repository.OrderItemRepository;
import org.likelion.likelionrecrud.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Member member = memberRepository.findById(orderSaveReqDto.memberId())
                .orElseThrow(()-> new IllegalArgumentException("해당 회원이 없습니다. id=" + orderSaveReqDto.memberId()));

        Order order = Order.builder()
                .member(member)
                .status(OrderStatus.ORDER)
                .build();

        orderRepository.save(order);

        for (int i = 0; i < orderSaveReqDto.itemIds().size(); i++) {
            Long itemId = orderSaveReqDto.itemIds().get(i);
            int count = orderSaveReqDto.counts().get(i);

            Item item = itemRepository.findById(itemId)
                    .orElseThrow(()-> new IllegalArgumentException("해당 상품이 없습니다. id=" + itemId));

            orderItemRepository.save(OrderItem.builder()
                    .item(item)
                    .order(order)
                    .count(count)
                    .orderPrice(item.getPrice())
                    .build());

            item.removeStock(count);
        }

    }

    public List<OrderInfoResDto> findOrderInfoByMemberId(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("해당 회원이 없습니다. id=" + memberId));
        List<OrderItem> allByOrderMember = orderItemRepository.findAllByOrderMember(member);

        List<OrderInfoResDto> orderInfoResDtosList = new ArrayList<>();
        for(OrderItem orderItem : allByOrderMember) {
            OrderInfoResDto o = new OrderInfoResDto(
                    member.getId(),
                    orderItem.getOrder().getId(),
                    new OrderItemResDto(
                            orderItem.getItem().getId(),
                            orderItem.getItem().getPrice(),
                            orderItem.getCount())
            );

            orderInfoResDtosList.add(o);
        }
        return orderInfoResDtosList;

    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new IllegalArgumentException("해당 주문이 없습니다. id=" + orderId));
        order.cancel();
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new IllegalArgumentException("해당 주문이 없습니다. id=" + orderId));
        orderRepository.delete(order);
    }


}
