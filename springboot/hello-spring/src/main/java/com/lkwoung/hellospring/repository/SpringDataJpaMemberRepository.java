package com.lkwoung.hellospring.repository;

import com.lkwoung.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JPA repository를 가지고 있으니까 spring이 알아서 등록해줌

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //이름의 규칙을 보고 sql를 짜줌
    //JPQL -> select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
