package com.mysite.rmss.controller.order;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.order.OrderSheetInfoDto;
import com.mysite.rmss.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders/order")
    public String order(@ModelAttribute OrderSheetInfoDto orderSheetInfoDto,
                        @CurrentMember Member currentMember) {

        if (currentMember == null) {
            return "redirect:/";
        }

        orderService.orderByCart(orderSheetInfoDto, currentMember.getUsername());
        // TODO: redirect 대신에 주문 완료 페이지로?
        return "redirect:/";
    }
}
