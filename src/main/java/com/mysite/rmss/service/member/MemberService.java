package com.mysite.rmss.service.member;

import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.member.MemberInfoResponseDto;
import com.mysite.rmss.dto.member.MemberSaveForm;
import com.mysite.rmss.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    public final MemberRepository memberRepository;
    public final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(MemberSaveForm form) {
        Member newMember = saveNewMember(form);
        memberRepository.save(newMember);
    }

    /**
     *
     * find Member Entity @param username
     * @return convert Entity to MemberInfoResponseDto
     */
    public MemberInfoResponseDto memberInfoFindByUsername(String username) {
        Member findMember = memberRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException(username + " 사용자를 찾을 수 없습니다."));

        return new MemberInfoResponseDto(findMember);
    }

    private Member saveNewMember(MemberSaveForm form) {
        return Member.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword1()))
                .email(form.getEmail())
                .build();
    }
}
