package org.likelion.jangsu.category.api.dto.request;


import org.likelion.jangsu.category.domain.CategoryType;

public record CategoryUpdateReqDto(CategoryType name, Long parent) {
}