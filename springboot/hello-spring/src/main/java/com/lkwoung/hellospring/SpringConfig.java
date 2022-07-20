package com.lkwoung.hellospring;

import com.lkwoung.hellospring.aop.TimeTraceAop;
import com.lkwoung.hellospring.repository.*;
import com.lkwoung.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration // spring이 뜰 때, configuration을 읽어오고
public class SpringConfig {
    /**
     * Jdbc template code
     *         // datasource 스프링 제공
     *         // 스프링 부트가 application properties 파일을 보고 스프링이 자체적으로 데이터 소스를 만들어줌
     *         // 그리고 주입해줌
     *         private DataSource dataSource;
     *         @Autowired
     *         public SpringConfig(DataSource dataSource) {
     *             this.dataSource = dataSource;
     *         }
    **/

    /**
     * //@PersistenceContext -> 생성자 쓰지않고 이렇게 DI해주어도 됨
     *     /*
     *         JPA code
     *     private EntityManager em;
     *     @Autowired
     *     public SpringConfig(EntityManager em) {
     *         this.em = em;
     *     }
    **/

    // spring data jpa
    // JpaRepository<Member, Long> 스프링에서 제공하는 jpa를 통해,
    // 인젝션을 받을 수 있음
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //

    @Bean // Bean을 등록하라는 것으로 인식 -> 등록
    public MemberService memberService(){
        /**
         *  JDBC, JDBC Template, JPA
         *  return new MemberService(memberRepository()); //ctrl+p 인자 타입 미리보기 단축키
         *  autowired 처럼 연결해줌
        **/
        // spring data jpa
        return new MemberService(memberRepository);
        //
    }

    /**    // AOP는 가시적으로 보이게 java로 등록해주는 것이 좋다.
    *  @Bean
    *  public TimeTraceAop timeTraceAop(){
    *      return new TimeTraceAop();
    *  }
    **/

    /**
     * @Bean
     *     public MemberRepository memberRepository() {
     *         // return new MemoryMemberRepository(); //메모리 리포지토리를 스프링 빈으로 등록
     *         // return new JdbcMemberRepository(dataSource); // 코드를 하나하나 고칠 일 없이 config만 수정하여 DB를 메모리DB에서 다른 실제DB로 변경하였음
     *         // return new JdbcTemplateMemberRepository(dataSource);
     *         // return new JpaMemberRepository(em);
     *     }
     */


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


