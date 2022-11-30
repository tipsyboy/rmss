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
            welcome = member.getUsername();
        }

        model.addAttribute("welcome", welcome);
        return "home";
    }

    // profile page
    @GetMapping("/profile/{username}")
    public String profile(@PathVariable("username") String username,
                          @CurrentMember Member currentMember,
                          Model model) {
        Member findMember = memberRepository.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException(username + " 사용자를 찾을 수 없습니다."));

        boolean isMyPage = currentMember != null && currentMember.getUsername().equals(findMember.getUsername());
        model.addAttribute("member", findMember);
        model.addAttribute("isMyPage", isMyPage);


        // TODO: url 매개변수로 받은 member id가 존재하는지 확인
        // TODO: 현재 로그인된 사용자가 있다면 profile 의 주인인지 확인한다.
        // TODO: Dto 변환 후 모델로 앞단에 전달한다

        return "members/profile";
    }
}
