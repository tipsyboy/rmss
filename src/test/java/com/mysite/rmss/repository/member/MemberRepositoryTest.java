package com.mysite.rmss.repository.member;

import com.mysite.rmss.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원_저장_조회() {
        // given
        Member member = Member.builder()
                .username("memberA")
                .email("123@gmail.com")
                .password("1")
                .build();

        // when
        Long memberId = memberRepository.save(member);
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저를 찾을 수 없습니다."));

        // then
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void 회원_아이디로_찾기() {
        // given
        String givenMemberName = "memberA";
        Member member = Member.builder()
                .username(givenMemberName)
                .email("123@gmail.com")
                .password("1")
                .build();
        memberRepository.save(member);

        // when
        Member findMember = memberRepository.findByName(givenMemberName)
                .orElseThrow(() -> new NoSuchElementException("찾을 수 없습니다."));

        // then
        assertThat(findMember.getUsername()).isEqualTo(givenMemberName);
    }
}