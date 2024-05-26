package org.likelion.likelionrecrud.order.api.dto.response;

public record OrderInfoResDto(

        Long memberId,
        Long orderId,
        OrderItemResDto orderItems

) {

}
