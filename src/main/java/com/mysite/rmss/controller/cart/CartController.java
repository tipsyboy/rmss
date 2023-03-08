package com.mysite.rmss.controller.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {

    @GetMapping("/pCart/{memberName}")
    public String viewCart(@PathVariable("memberName") String memberName) {


        // TODO: 권한 없으면 접근 불가
        return "cart/cartView";
    }
}
