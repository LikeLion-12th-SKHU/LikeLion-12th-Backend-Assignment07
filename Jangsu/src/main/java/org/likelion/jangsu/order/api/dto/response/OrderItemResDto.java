package org.likelion.jangsu.order.api.dto.response;

public record OrderItemResDto(Long itemId, int orderPrice, int count) {
}