package com.mysite.rmss.controller.order;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.order.OrderRequestDto;
import com.mysite.rmss.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/orderSheet")
    public String viewOrderSheet(@ModelAttribute OrderRequestDto orderRequestDto,
                                 @CurrentMember Member member) {
        if (member == null) {
            return "redirect:/members/login";
            // TODO: 이 로그인 이후에 상품 페이지로 다시 돌아가게 하고 싶은데, 어떻게 할까
        }
        
        // 주문
        orderService.order(orderRequestDto);

        return "redirect:/"; // TODO: 주문 완료 페이지로 이동해야함.
    }
}
