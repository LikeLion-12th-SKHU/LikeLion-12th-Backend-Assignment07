package org.likelion.likelionrecrud.category.api.dto.request;

import lombok.Builder;

@Builder
public record CategoryUpdateReqDto(String name) {
    public CategoryUpdateReqDto{
    }
}
