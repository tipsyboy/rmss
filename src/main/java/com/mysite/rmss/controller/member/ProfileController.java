package com.mysite.rmss.controller.member;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.member.MemberInfoResponseDto;
import com.mysite.rmss.dto.member.MemberProfileEditForm;
import com.mysite.rmss.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
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

    @GetMapping("/settings")
    public String editForm(@ModelAttribute("form") MemberProfileEditForm form,
                           @CurrentMember Member member,
                           Model model) {

        if (member == null) {
            // 로그인 멤버 없음
            return "redirect:/";
        }

        MemberInfoResponseDto dto = memberService.findById(member.getId());
        form.mappingProfileInfo(dto);
        model.addAttribute("member", member);

        return "members/profileEdit";
    }

    @PostMapping("/settings")
    public String edit(@Valid @ModelAttribute("form") MemberProfileEditForm form,
                       BindingResult bindingResult,
                       @CurrentMember Member member,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "members/profileEdit";
        }

        memberService.updateProfile(member.getId(), form);

        redirectAttributes.addFlashAttribute("message", "프로필을 수정하였습니다.");
        return "redirect:/profile/settings";
    }
}
