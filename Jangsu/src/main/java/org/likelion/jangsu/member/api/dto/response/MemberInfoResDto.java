package org.likelion.jangsu.member.api.dto.response;

import lombok.Builder;
import org.likelion.jangsu.member.domain.Member;
import org.likelion.jangsu.member.domain.Part;

@Builder
public record MemberInfoResDto (Long memberId,String name, int age, Part part){
    public static MemberInfoResDto from(Member member){
        return MemberInfoResDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .part(member.getPart())
                .build();
    }
}
