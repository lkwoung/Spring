package com.lkwoung.hellospring.repository;

import com.lkwoung.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional null로 반환되는 값을 optional로 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
