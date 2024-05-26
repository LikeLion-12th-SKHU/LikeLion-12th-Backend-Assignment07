package org.likelion.likelionassignment07crud.order.api.request;

import java.util.List;

public record OrderSaveReqDto(
        Long memberId,
        List<Long> itemIds,
        List<Integer> counts
) {
}

