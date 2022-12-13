package com.mysite.rmss.controller.shop;

import com.mysite.rmss.dto.shop.ShopOpenForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/shop")
@RequiredArgsConstructor
@Controller
public class ShopController {

    @GetMapping("/open")
    public String openShopForm(@ModelAttribute ShopOpenForm shopOpenForm) {
        return "shop/shopOpenForm";
    }

    @PostMapping("/open")
    public String openShop(@Valid @ModelAttribute ShopOpenForm shopOpenForm,
                           BindingResult bindingResult) {

        // TODO: OPEN FORM 검증 - 에러 있으면 처리 - 뭘 검증 해야할까?
        if (bindingResult.hasErrors()) {
            log.info("bindingResult 에러있음={}", bindingResult);
            return "redirect:/";
        }
        // TODO: repository 생성
        // TODO: service 계층으로 쇼핑몰 개설 인계
        // TODO: Redirect shop page or 관리 페이지 - url 처리 방법
        // TODO: 생성 html 페이지 만들기
        // TODO: 테스팅
        return "redirect:/";
    }
}
