package com.mysite.rmss.controller;

import com.mysite.rmss.dto.member.MemberSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/members")
@Controller
public class MemberController {

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

        if (!form.getPassword1().equals(form.getPassword2())) {
            // TODO: errors.properties 정의하기?
            bindingResult.rejectValue("password2", "passwordNotEquals",
                    "비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요.");
            return "members/signupForm";
        }
        
        // TODO: 중복 회원가입 예외처리

        // TODO: 회원가입 성공 로직
        return "redirect:/";
    }
}
