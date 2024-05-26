package org.likelion.jangsu.member.domain.repository;

import org.likelion.jangsu.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}