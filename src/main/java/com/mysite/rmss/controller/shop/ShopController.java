package com.mysite.rmss.controller.shop;

import com.mysite.rmss.dto.shop.ShopOpenForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/shop")
@RequiredArgsConstructor
@Controller
public class ShopController {

    @GetMapping("/open")
    public String openShop(@ModelAttribute ShopOpenForm shopOpenForm) {
        return "shop/shopOpenForm";
    }

}
