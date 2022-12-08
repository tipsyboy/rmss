package com.mysite.rmss.controller;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
            log.info("memberInfo={}", member.getId());
            log.info("memberInfo={}", member.getUsername());
            log.info("memberInfo={}", member.getEmail());
            log.info("memberInfo={}", member.getPassword());
            log.info("memberInfo={}", member.getBio());
            welcome = member.getUsername();

        }


        model.addAttribute("welcome", welcome);
        return "home";
    }
}
