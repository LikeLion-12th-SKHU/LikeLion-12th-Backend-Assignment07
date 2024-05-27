package org.likelion.likelionassignmentcrud.member.api.dto.request;

import org.likelion.likelionassignmentcrud.member.domain.Part;

public record MemberUpdateReqDto(
        String name,
        int age,
        Part part
) {
}
