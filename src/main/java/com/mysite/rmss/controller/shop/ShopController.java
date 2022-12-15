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
    public String openShopForm(@ModelAttribute ShopOpenForm shopOpenForm) {
        return "shop/shopOpenForm";
    }

    @PostMapping("/open")
    public String openShop(@Valid @ModelAttribute ShopOpenForm shopOpenForm,
                           BindingResult bindingResult,
                           @CurrentMember Member currentMember) {

        // TODO: OPEN FORM 검증 - 에러 있으면 처리 - 뭘 검증 해야할까?
        // TODO: 중복 URL, 샵 이름, bean validation
        if (bindingResult.hasErrors()) {
            log.info("bindingResult 에러있음={}", bindingResult);
            return "redirect:/";
        }

        // TODO: Redirect shop page or 관리 페이지 - url 처리 방법
        shopService.openShop(currentMember.getId(), shopOpenForm);
        return "redirect:/shop/" + URLEncoder.encode(shopOpenForm.getUrl(), StandardCharsets.UTF_8);

        // TODO: 생성 html 페이지 만들기
        // TODO: 테스팅
    }
}
