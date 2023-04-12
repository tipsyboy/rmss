package com.mysite.rmss.controller.order;

import com.mysite.rmss.dto.order.OrderSheetInfoDto;
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

    // order sheet
//    @GetMapping("/orders/ordersheet")
//    public String viewOrderSheet() {
//    }

    @PostMapping("/orders/order")
    public String order(@ModelAttribute OrderSheetInfoDto orderSheetInfoDto) {
        for (Long n : orderSheetInfoDto.getIdList()) {
            log.info("cartItemNo: {}", n );
        }

        return "redirect:/";
    }
}
