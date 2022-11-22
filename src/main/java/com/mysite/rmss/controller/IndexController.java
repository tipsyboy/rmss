package com.mysite.rmss.controller;

import com.mysite.rmss.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
