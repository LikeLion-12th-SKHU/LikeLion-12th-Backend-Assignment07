package org.likelion.likelionassignment07crud.item.api.dto.request;

public record ItemSaveReqDto (
        String name,
        int price,
        int stockQuantity
){
}
