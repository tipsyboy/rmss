package com.mysite.rmss.controller.order;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.cart.AddItemToCartRequestDto;
import com.mysite.rmss.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/cart/add")
    public String addItemToCartRequest(@Valid @ModelAttribute AddItemToCartRequestDto addItemToCartRequestDto,
                                       BindingResult bindingResult,
                                       @CurrentMember Member member,
                                       RedirectAttributes redirectAttributes) {

        if (member == null) {
            return "redirect:/members/login";
            // TODO: 이 로그인 이후에 상품 페이지로 다시 돌아가게 하고 싶은데, 어떻게 할까
        }

//        if (bindingResult.hasErrors()) {
//            // TODO: 이전페이지로 돌아가야하는데..
//        }

        orderService.addItemToCart(addItemToCartRequestDto);

        // RedirectAttributes - addAttribute / addFlashAttribute
        redirectAttributes.addAttribute("memberName", member.getUsername());
        return "redirect:/pCart/{memberName}"; // 일단 장바구니로 리다이렉트
    }
}
