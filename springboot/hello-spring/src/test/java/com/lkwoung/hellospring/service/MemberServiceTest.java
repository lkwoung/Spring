package com.lkwoung.hellospring.service;

import com.lkwoung.hellospring.domain.Member;
import com.lkwoung.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // 새로운 인스턴스 memberservice 인스턴스와 다른 인스턴스임 따라서 같은 인스턴스로 테스트하는 것이 좋음
    // 지금은 자료구조 해시맵이 static이라서 문제가 없지만 static이 없어지면은 다른 인스턴스가 됨.
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);// 레포지토리를 외부에서 넣어주는 방식으로 바꾸어준다.
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given 상황이 주어져서
        Member member = new Member();
        member.setName("hello");

        //when 이런 처리가 이루어졌을 때
        Long saveId = memberService.join(member);

        //then 결과
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    //테스트는 함수이름이 한글이여도 상관없음
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");
        //when
        //1
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //2
        // assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //3
       /* try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            // 예외발생 성공
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}