package com.mysite.rmss.service.member;

import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.member.MemberInfoResponseDto;
import com.mysite.rmss.dto.member.MemberProfileEditForm;
import com.mysite.rmss.dto.member.MemberSaveForm;
import com.mysite.rmss.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    public final MemberRepository memberRepository;
    public final PasswordEncoder passwordEncoder;
    public final MemberSecurityService memberSecurityService;

    @Transactional
    public void signup(MemberSaveForm form) {
        Member newMember = saveNewMember(form);
        memberRepository.save(newMember);
        loginAfterSignup(form);
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

    // update profile
    @Transactional
    public void updateProfile(Long id, MemberProfileEditForm form) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. id=" + id));

        findMember.updateProfile(form.getBio());
    }

    public MemberInfoResponseDto findById(Long id) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. id= " + id));

        return new MemberInfoResponseDto(findMember);
    }

    @Transactional
    public void passwordEdit(Long id, String newPassword) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. id= " + id));

        findMember.editPassword(passwordEncoder.encode(newPassword));
    }

    private Member saveNewMember(MemberSaveForm form) {
        return Member.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword1()))
                .email(form.getEmail())
                .build();
    }

    /**
     * 회원가입 이후 자동 로그인
     */
    private void loginAfterSignup(MemberSaveForm form) {
        UserDetails principal = memberSecurityService.loadUserByUsername(form.getUsername());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, form.getPassword1());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
