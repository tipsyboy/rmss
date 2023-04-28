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
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final MemberSaveFormValidator memberSaveFormValidator;

    @InitBinder("saveForm") // Think: 이렇게 target 을 설정해서 하는거면.. initBinder 를 사용할 이유가 없지않나?
    public void init(WebDataBinder dataBinder) {
        log.info("webDataBinder={}, target={}", dataBinder, dataBinder.getTarget());
        dataBinder.addValidators(memberSaveFormValidator);
    }

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("saveForm") MemberSaveForm saveForm) {
        return "members/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("saveForm") MemberSaveForm saveForm,
                         BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "members/signupForm";
        }

        // 회원가입 처리
        memberService.signup(saveForm);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "members/loginForm";
    }
}
