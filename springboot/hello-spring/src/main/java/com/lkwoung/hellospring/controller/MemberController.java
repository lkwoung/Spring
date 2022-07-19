package com.lkwoung.hellospring.controller;

import com.lkwoung.hellospring.domain.Member;
import com.lkwoung.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // spring 컨테이너가 @Controller을 보고 MemberController객체를 생성하고 컨테이너에 넣어 관리해준다.
// spring 컨테이너에서 spring bean이 관리된다라고 표현
public class MemberController {
    // spring 컨테이너에 등록이되면 spring 컨테이너에의해 관리되도록 바꾸어야함
    // 아래는 spring 컨테이너의 관리를 벗어나 new로 생성하기보단 spring에 하나만 등록해서 쓰는 것이 바람직하다.
    // private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    @Autowired // spring 컨테이너가 뜰 때 생성자가 실행
    // @Autowired 되어있으면 spring이 memberService를 인스턴스를 알아서 가져와 주입(연결)해준다.
    // 의존성 주입 DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 같은 url로 get, post에 따라 다르게 매핑할 수 있다.
    @GetMapping("/members/new") // url get방식을 통해 들어오기 때문에 이 함수가 먼저 실행이되고
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 입력된 문자를 post(데이터를 form같은 곳에 넣어서 전달)형식으로 보내 이 함수가 실행이된다.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 홈 화면으로 이동
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // 모든 맴버가 담긴 리스트를 그대로 모델에 담아서 템플릿(뷰)에 넘김
        return "members/memberList";
    }
}

// @Controller, @Repository, @Service 매우 정형화된 패턴.
// spring 컨테이너가 실행될 때 한번에 관리들어감

// 어노테이션을 사용한 방식이 컴포넌트 스캔과 자동 의존관계 설정임
// @Controller 등등에 @Component가 포함되어있음
// 스프링이 component와 관련된 (com.lkwoung.hellospring 범위내의) 객체들을 모두 컨테이너에 포함시킴
// 싱글톤의 형식으로 등록됨. -> 공유
// 설정으로 싱글톤이 아니게 할 수 있음
// Autowired가 이들의 관계를 연결해줌

// 이 외에 자바 코드로 직접 스프링 빈을 등록할 수 있음