package com.mysite.rmss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
public class MemberController {

    @GetMapping("/signup")
    public String signupForm() {
        return "members/signupForm";
    }
}
