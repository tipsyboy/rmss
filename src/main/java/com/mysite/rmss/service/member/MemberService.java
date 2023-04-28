package com.mysite.rmss.service.member;

import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.member.MemberInfoResponseDto;
import com.mysite.rmss.dto.member.MemberProfileEditForm;
import com.mysite.rmss.dto.member.MemberSaveForm;
import com.mysite.rmss.file.FileStore;
import com.mysite.rmss.file.UploadFile;
import com.mysite.rmss.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberSecurityService memberSecurityService;
    private final FileStore fileStore;

    @Transactional
    public void signup(MemberSaveForm form) throws IOException {
        Member newMember = saveNewMember(form);
        memberRepository.save(newMember);
        loginAfterSignup(form);
    }

    /**
     *
     * find Member Entity @param username
     * @return convert Entity to MemberInfoResponseDto
     */
    public MemberInfoResponseDto findMemberInfoByUsername(String username) {
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

    private Member saveNewMember(MemberSaveForm form) throws IOException {
        UploadFile memberProfileImage = fileStore.storeFile(form.getImgFile());

        return Member.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword1()))
                .email(form.getEmail())
                .memberProfileImage(memberProfileImage)
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
