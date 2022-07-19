package com.lkwoung.hellospring.service;

import com.lkwoung.hellospring.domain.Member;
import com.lkwoung.hellospring.repository.MemberRepository;
import com.lkwoung.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        // 이렇게 직접 메모리레포지토리를 생성하지 않고 외부에서 넣어주는 방식을 DI라고 한다.
        this.memberRepository = memberRepository;
    }

/**
 * 회원가입
 * */
    public Long join(Member member){
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        //optional 이 객체를 한번 감싼 덕에 아래와 같은 함수를 사용할 수 있음. 아니였으면 null인지 아닌지 따로 확인했어야함.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        } );
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
