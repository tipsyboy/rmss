package com.mysite.rmss.controller;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.shop.ShopInfoResponseDto;
import com.mysite.rmss.repository.member.MemberRepository;
import com.mysite.rmss.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ShopService shopService;

    @GetMapping("/")
    public String home(@CurrentMember Member member, Model model) {
        String welcome = "손";
        if (member != null) {
            welcome = member.getUsername();
        }
        List<ShopInfoResponseDto> shopList = shopService.findAllShopList();

        model.addAttribute("welcome", welcome);
        model.addAttribute("shopList", shopList);
        return "home";
    }
}
