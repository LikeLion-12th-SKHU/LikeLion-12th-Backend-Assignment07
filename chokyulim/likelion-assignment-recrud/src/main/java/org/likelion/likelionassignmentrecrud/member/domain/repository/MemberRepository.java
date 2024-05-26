package org.likelion.likelionassignmentrecrud.member.domain.repository;

import org.likelion.likelionassignmentrecrud.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
