package com.lkwoung.hellospring.service;

import com.lkwoung.hellospring.domain.Member;
import com.lkwoung.hellospring.repository.MemberRepository;
import com.lkwoung.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository){
        // 이렇게 직접 메모리레포지토리를 생성하지 않고 외부에서 넣어주는 방식을 DI라고 한다.
        this.memberRepository = memberRepository;
    }

/**
 * 회원가입
 * */
    public Long join(Member member){
        /**
        * Optional<Member> result = memberRepository.findByName(member.getName());
        * //optional 이 객체를 한번 감싼 덕에 아래와 같은 함수를 사용할 수 있음. 아니였으면 null인지 아닌지 따로 확인했어야함.
        * result.ifPresent(m -> {
        *     throw new IllegalStateException("이미 존재하는 회원입니다.");
        * } );
        */


        /** AOP를 안쓰면 수행시간을 알아보는 작업 등을 이렇게 일일히 작업을 해야함.
         *long start = System.currentTimeMillis();
         *         try{
         *             validateDuplicateMember(member);
         *             memberRepository.save(member);
         *             return member.getId();
         *         }finally {
         *             long finish = System.currentTimeMillis();
         *             long timeMs = finish - start;
         *             System.out.println("join ="+ timeMs + "ms");
         *         }
         * 문제
         * 회원가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아니다.
         * 시간을 측정하는 로직은 공통 관심 사항이다.
         * 시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어렵다.
         * 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다.
         * 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다.
         */


            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는지 확인 / 중복된 이름x
        memberRepository.findByName(member.getName()) // 반환형이 이미 optional이기 때문에 이처럼 사용해도 됨
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
