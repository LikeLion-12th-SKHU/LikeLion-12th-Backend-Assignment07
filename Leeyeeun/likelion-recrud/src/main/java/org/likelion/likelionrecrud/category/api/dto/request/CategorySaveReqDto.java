package org.likelion.likelionrecrud.category.api.dto.request;

import lombok.Builder;

@Builder
public record CategorySaveReqDto(

        Long parentId,
        String name

) {
}
