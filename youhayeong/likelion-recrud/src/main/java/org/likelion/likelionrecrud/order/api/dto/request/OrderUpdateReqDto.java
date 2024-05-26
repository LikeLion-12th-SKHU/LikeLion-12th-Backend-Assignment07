package org.likelion.likelionrecrud.order.api.dto.request;

public record OrderUpdateReqDto(
        Long memberId,
        Long itemId
) {
}
