package org.likelion.jangsu.member.api.dto.request;

import org.likelion.jangsu.member.domain.Part;

public record MemberUpdateReqDto(String name, int age, Part part) {
}
