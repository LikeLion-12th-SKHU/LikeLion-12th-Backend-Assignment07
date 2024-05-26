package org.likelion.likelionassignmentrecrud.member.api.dto.request;

import org.likelion.likelionassignmentrecrud.member.domain.Part;

public record MemberUpdateReqDto(
        String name,
        int age,
        Part part
) {
}
