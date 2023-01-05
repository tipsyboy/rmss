package com.mysite.rmss.controller.order;

import com.mysite.rmss.dto.order.OrderRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class OrderController {

    @GetMapping("/order/orderSheet")
    public String viewOrderSheet(@ModelAttribute OrderRequestDto orderRequestDto) {
        log.info("orderRequestDto.getItemId()={}",orderRequestDto.getItemId());
        log.info("orderRequestDto.getQuantity()={}",orderRequestDto.getQuantity());
        log.info("orderRequestDto.getMemberId()={}",orderRequestDto.getMemberId());

        return "redirect:/";
    }
}
