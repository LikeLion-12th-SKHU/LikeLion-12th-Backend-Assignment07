package org.likelion.likelionassignmentrecrud.category.api.dto.request;

import lombok.Builder;
import org.likelion.likelionassignmentrecrud.category.domain.Category;

@Builder
public record CategoryUpdateReqDto(
        Long parentId,
        String name
) {}
