package org.likelion.jangsu.category.api.dto.request;

import org.likelion.jangsu.category.domain.CategoryType;

public record CategorySaveReqDto(CategoryType name, Long parent) {
}