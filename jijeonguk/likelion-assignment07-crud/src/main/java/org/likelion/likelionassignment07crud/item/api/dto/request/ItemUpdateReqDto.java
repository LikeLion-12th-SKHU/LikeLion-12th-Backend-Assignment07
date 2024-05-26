package org.likelion.likelionassignment07crud.item.api.dto.request;

public record ItemUpdateReqDto(
        String name,
        int price,
        int stockQuantity
){
}