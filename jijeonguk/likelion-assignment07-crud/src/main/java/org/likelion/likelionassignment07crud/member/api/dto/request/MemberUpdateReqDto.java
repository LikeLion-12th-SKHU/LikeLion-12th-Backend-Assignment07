package org.likelion.likelionassignment07crud.member.api.dto.request;

import org.likelion.likelionassignment07crud.member.domain.Part;

public record MemberUpdateReqDto(
        String name,
        int age,
        Part part
) {
}
