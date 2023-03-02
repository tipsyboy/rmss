package com.mysite.rmss.controller.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {

    @GetMapping("/cart/{memberName}")
    public String viewCart(@PathVariable("memberName") String memberName) {

        // TODO: 권한 없으면 접근 불가
        return "cart/cartView";
    }

    public String addItemToCart() {
        /**
         *  TODO: 로그인된 사용자의 장바구니로 상품을 추가한다.
         * 1. 로그인 되어 있는지 확인
         * 2. 상품 정보 - (itemId, price, quantity), 쇼핑몰 정보 - (아이템에서 역추적?), 회원 정보 - (memberId)를 종합해서 addCartRequestDto 를 생성하고 Service 로 보내면 풀어 헤쳐서 OrderItem 을 만들고 Cart 에 추가해 놓는다.
         */

        return "";
    }
}
