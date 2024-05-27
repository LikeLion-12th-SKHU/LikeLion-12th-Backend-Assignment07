package org.likelion.likelionrecrud.order.api.dto.response;

public record OrderItemResDto(

        Long itemId,

        int orderPrice,
        int count

) {

}
