package org.likelion.likelionassignmentrecrud.order.api.dto.response;

// 개별 주문 항목의 상세 정보
public record OrderItemResDto (
        Long itemid,
        int orderPrice,
        int count
) {
}
