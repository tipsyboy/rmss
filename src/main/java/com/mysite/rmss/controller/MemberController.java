package com.mysite.rmss.controller;

import com.mysite.rmss.controller.validator.MemberSaveFormValidator;
import com.mysite.rmss.dto.member.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberSaveFormValidator memberSaveFormValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(memberSaveFormValidator);
    }

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("form") MemberSaveForm form) {
        return "members/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("form") MemberSaveForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "members/signupForm";
        }

        // TODO: 회원가입 성공 로직
        return "redirect:/";
    }
}
