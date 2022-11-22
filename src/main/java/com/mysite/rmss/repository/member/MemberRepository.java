package com.mysite.rmss.repository.member;

import com.mysite.rmss.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final EntityManager em;

    // 저장
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    // id 값으로 찾기
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    // 유저이름 (로그인 아이디)으로 찾기
    public Optional<Member> findByName(String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList()
                .stream().findAny();
    }

    public boolean existsByName(String username) {
        List<Long> result = em.createQuery("select m.id from Member m where m.username = :username", Long.class)
                .setParameter("username", username)
                .getResultList();

        return result.size() > 0;
    }
}
