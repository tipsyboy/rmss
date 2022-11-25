package com.mysite.rmss.controller;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.config.auth.dto.CurrentMemberDto;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(Principal principal,
                       @CurrentMember Member member) {
        log.info("principal={}", principal);
        if (principal != null) {
            log.info("principal 을 받아서 사용하는 경우");
            String name = principal.getName();
            Member findMember = memberRepository.findByName(name)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
            log.info("findMember.getUsername()={}", findMember.getUsername());
            log.info("findMember.getPassword()={}", findMember.getPassword());
            log.info("findMember.getEmail()={}", findMember.getEmail());
        }

        log.info("member={}", member);
        if (member!=null) {
            log.info("현재 인증된 사용자의 객체를 받아서 사용하는 경우");
            log.info("member.getUsername()={}", member.getUsername());
            log.info("member.getPassword()={}", member.getPassword());
            log.info("member.getEmail()={}", member.getEmail());
        }
        return "home";
    }
}
