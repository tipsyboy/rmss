package com.mysite.rmss.controller.cart;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.cart.AddItemToCartRequestDto;
import com.mysite.rmss.dto.cart.CartItemInfoResponseDto;
import com.mysite.rmss.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @GetMapping("/pCart/delete/{cartItemId}")
    public String deleteCartItem(@PathVariable("cartItemId") Long cartItemId,
                                 @CurrentMember Member member,
                                 RedirectAttributes redirectAttributes) {
        cartService.deleteCartItem(member.getUsername(), cartItemId);

        redirectAttributes.addAttribute("memberName", member.getUsername());
        return "redirect:/pCart/{memberName}"; // 일단 장바구니로 리다이렉트
    }

    // add item to cart
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

        cartService.addItemToCart(addItemToCartRequestDto);

        // RedirectAttributes - addAttribute / addFlashAttribute
        redirectAttributes.addAttribute("memberName", member.getUsername());
        return "redirect:/pCart/{memberName}"; // 일단 장바구니로 리다이렉트
    }
}
