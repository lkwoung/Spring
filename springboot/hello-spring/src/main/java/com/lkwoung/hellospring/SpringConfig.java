package com.lkwoung.hellospring;

import com.lkwoung.hellospring.repository.MemberRepository;
import com.lkwoung.hellospring.repository.MemoryMemberRepository;
import com.lkwoung.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // spring이 뜰 때, configuration을 읽어오고
public class SpringConfig {
    @Bean // Bean을 등록하라는 것으로 인식 -> 등록
    public MemberService memberService(){
        return new MemberService(memberRepository()); //ctrl+p 인자 타입 미리보기 단축키
        // autowired 처럼 연결해줌
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 1. memberservice와 memberrepository를 스프링 빈에 등록
    // 2. 스프링 빈에 등록된 memberrepository를 service에 넣어줌(해당클래스 생성자)
    // 3. xml(생략 현재는 잘 사용하지 않음)

    // DI에는 필드 주입 (@Autowired 객체 변수) -> 중간에 변경이 불가능함
    // setter 주입 @Autowired + set 함수를 통해서 의존성 주입 -> public하게 열려있음
    // 생성자 주입 3가지 방법이 있다. -> 권장

    // 호출하지 않아야될 메서드들은 호출되지 않게 설계
    // 변경을 막고 확장에는 열려있어야함.

    // 실무에서는 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용
    // 정형화되지 않거나,상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록 -> 나중에 변경이 필요할 때 유리함
}
