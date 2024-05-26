package org.likelion.likelionassignmentrecrud.order.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderListResDto(
        List<OrderInfoResDto> orderList
) {
    public static OrderListResDto from(List<OrderInfoResDto> orderList) {
        return OrderListResDto.builder() // 개별 주문 정보
                .orderList(orderList) // 개별 주문 항목의 정보
                .build();
    }
}
