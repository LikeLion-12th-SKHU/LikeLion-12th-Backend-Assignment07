package org.likelion.jangsu.order.api.dto.response;

import java.util.List;

public record OrderInfoResDto(Long memberId, Long orderId, List<OrderItemResDto> orderItems) {
}
