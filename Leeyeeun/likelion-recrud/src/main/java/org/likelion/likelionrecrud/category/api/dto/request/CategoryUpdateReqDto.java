package org.likelion.likelionrecrud.category.api.dto.request;

public record CategoryUpdateReqDto(

        Long parentId,
        String name

) {
}
