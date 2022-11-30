package com.mysite.rmss.controller.member;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.controller.validator.MemberSaveFormValidator;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.member.MemberInfoResponseDto;
import com.mysite.rmss.dto.member.MemberSaveForm;
import com.mysite.rmss.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final MemberSaveFormValidator memberSaveFormValidator;

    @InitBinder("MemberSaveForm") // Think: 이렇게 target 을 설정해서 하는거면.. initBinder 를 사용할 이유가 없지않나?
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(memberSaveFormValidator);
    }

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("form") MemberSaveForm form) {
        return "members/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("form") MemberSaveForm form,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "members/signupForm";
        }

        // TODO: 회원가입 후 자동로그인은 도대체 어떻게 구현할 수 있을까..?
        // 회원가입 처리
        memberService.signup(form);

        return "redirect:/members/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "members/loginForm";
    }

    // profile page
    @GetMapping("/profile/{username}")
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
