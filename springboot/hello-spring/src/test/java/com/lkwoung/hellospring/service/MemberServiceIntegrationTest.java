package com.lkwoung.hellospring.service;

import com.lkwoung.hellospring.domain.Member;
import com.lkwoung.hellospring.repository.MemberRepository;
import com.lkwoung.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 테스트는 반복할 수 있게 설계해야함.
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService; //기존의 코드들은 생성자를 통한 주입이 좋지만 테스트 코드에서는 필드 인젝션으로 편하게 진행
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        Member member = new Member();
        member.setName("hello");

        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void joinReduplication(){

        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}