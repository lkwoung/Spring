package com.lkwoung.hellospring.repository;

import com.lkwoung.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository {
    // JPA는 em으로 모든 동작을 함
    // 이미 JPA 설정파일을 읽고 스프링이 em객체를 만들어줌.
    // 그걸 인젝션 해주면 됨
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    // 저장, 조희, 업데이트 등은 자동으로 됨 sql 필요없음 -> pk 기반
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // spring data jpa를 사용하면 밑에 두개도 sql를 안 짜도 됨
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 객체 자체를 select함
        // table 대상으로 sql을 날리는 것이아니라 객체entity를 대상으로 날림
        return em.createQuery("select m from Member  m", Member.class).getResultList();
    }
}
