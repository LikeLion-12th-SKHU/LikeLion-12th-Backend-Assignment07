package org.likelion.likelionrecrud.item.api.dto.request;

import java.util.List;

public record ItemSaveReqDto(

        String name,
        int price,
        int stockQuantity
) {
}
