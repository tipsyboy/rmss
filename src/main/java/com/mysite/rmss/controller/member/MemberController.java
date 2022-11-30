package com.mysite.rmss.controller.member;

import com.mysite.rmss.controller.validator.MemberSaveFormValidator;
import com.mysite.rmss.dto.member.MemberSaveForm;
import com.mysite.rmss.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
}
