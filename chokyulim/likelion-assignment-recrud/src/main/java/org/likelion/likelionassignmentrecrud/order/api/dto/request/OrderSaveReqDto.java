package org.likelion.likelionassignmentrecrud.order.api.dto.request;

import java.util.List;

public record OrderSaveReqDto(
        Long memberId,
        List<Long> itemIds,
        List<Integer> counts
) {
}
