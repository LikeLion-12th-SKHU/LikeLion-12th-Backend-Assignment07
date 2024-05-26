package org.likelion.likelionassignment07crud.member.domain.repository;

import org.likelion.likelionrecrud.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
