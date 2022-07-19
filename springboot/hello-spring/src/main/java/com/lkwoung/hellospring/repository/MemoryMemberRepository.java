package com.lkwoung.hellospring.repository;

import com.lkwoung.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // 해시맵 자료구조에 객체 저장 (메모리에), 실무에서는 concurrentHashmap을 사용
    private static long sequence = 0L; // 동시성 문제를 고려하여 atom long을 해야하지만 예제이므로 넘어감

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 객체가 없어서 null이여도 optional로 감싸면 클라이언트에서 작업을 할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 람다함수 아무나 찾으면 반환됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
