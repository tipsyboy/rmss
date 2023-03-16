package com.mysite.rmss.controller.cart;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.cart.CartItemInfoResponseDto;
import com.mysite.rmss.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/pCart/{memberName}")
    public String viewCart(@PathVariable("memberName") String memberName,
                           Model model) {

        List<CartItemInfoResponseDto> cartItemInfoResponseDtos = cartService.viewCartByMemberName(memberName);

        model.addAttribute("cartItemInfoResponseDtos", cartItemInfoResponseDtos);
        // TODO: 권한 없으면 접근 불가
        return "cart/cartView";
    }

    @GetMapping("/pCart/delete/{orderItemId}")
    public String deleteCartItem(@PathVariable("orderItemId") Long orderItemId,
                                 @CurrentMember Member member,
                                 RedirectAttributes redirectAttributes) {
        cartService.deleteCartItem(member.getUsername(), orderItemId);

        redirectAttributes.addAttribute("memberName", member.getUsername());
        return "redirect:/pCart/{memberName}"; // 일단 장바구니로 리다이렉트
    }
}
