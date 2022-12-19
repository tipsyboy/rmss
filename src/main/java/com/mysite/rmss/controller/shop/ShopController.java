package com.mysite.rmss.controller.shop;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.controller.validator.ShopOpenFormValidator;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.shop.ShopOpenForm;
import com.mysite.rmss.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequestMapping("/shop")
@RequiredArgsConstructor
@Controller
public class ShopController {

    private final ShopOpenFormValidator shopOpenFormValidator;
    private final ShopService shopService;

    @InitBinder("shopOpenForm")
    public void openFormValidation(WebDataBinder dataBinder) {
        dataBinder.addValidators(shopOpenFormValidator);
    }

    @GetMapping("/open")
    public String openShopForm(@ModelAttribute ShopOpenForm shopOpenForm,
                               @CurrentMember Member currentMember) {
        if (currentMember.getShop() != null) {
            // TODO: 접근 불가 - 이미 쇼핑몰이 있는 경우 Shop 관리 페이지로 리다이렉트, 시큐리티로 해결할 수 있나?
            return "redirect:/";
        }

        return "shop/shopOpenForm";
    }

    @PostMapping("/open")
    public String openShop(@Valid @ModelAttribute ShopOpenForm shopOpenForm,
                           BindingResult bindingResult,
                           @CurrentMember Member currentMember) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult 에러있음={}", bindingResult);
            return "shop/shopOpenForm";
        }

        shopService.openShop(currentMember.getId(), shopOpenForm);
        return "redirect:/shop/" + URLEncoder.encode(shopOpenForm.getUrl(), StandardCharsets.UTF_8);


        // TODO: 생성 html 페이지 만들기
        // TODO: 테스팅
    }
}
