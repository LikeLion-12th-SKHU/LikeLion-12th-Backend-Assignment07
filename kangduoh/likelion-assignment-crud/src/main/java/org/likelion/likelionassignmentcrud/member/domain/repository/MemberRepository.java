package org.likelion.likelionassignmentcrud.member.domain.repository;

import org.likelion.likelionassignmentcrud.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
