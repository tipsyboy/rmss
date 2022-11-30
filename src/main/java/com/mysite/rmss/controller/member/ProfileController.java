package com.mysite.rmss.controller.member;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.member.MemberInfoResponseDto;
import com.mysite.rmss.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/profile")
@RequiredArgsConstructor
@Controller
public class ProfileController {

    private final MemberService memberService;

    // profile page
    @GetMapping("/{username}")
    public String profile(@PathVariable("username") String username,
                          @CurrentMember Member currentMember,
                          Model model) {

        MemberInfoResponseDto findMemberDto = memberService.memberInfoFindByUsername(username);

        boolean isMyPage = currentMember != null && currentMember.getUsername().equals(findMemberDto.getUsername());
        model.addAttribute("member", findMemberDto);
        model.addAttribute("isMyPage", isMyPage);

        // TODO: url 매개변수로 받은 member id가 존재하는지 확인
        // TODO: 현재 로그인된 사용자가 있다면 profile 의 주인인지 확인한다.
        // TODO: Dto 변환 후 모델로 앞단에 전달한다

        return "members/profile";
    }
}
