package org.likelion.likelionrecrud.order.api.dto.response;

//개별 주문 항목의 상세 정보
public record OrderItemResDto(
    Long itemId,
    int orderPrice,
    int count
) {

}
