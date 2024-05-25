package org.likelion.jangsu.member.api.dto.request;

import org.likelion.jangsu.member.domain.Part;

public record MemberSaveReqDto (String name, int age, Part part){
}