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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(@CurrentMember Member member, Model model) {
        String welcome = "손";
        if (member != null) {
            welcome = member.getUsername();
        }

        model.addAttribute("welcome", welcome);
        return "home";
    }
}
