package com.mysite.rmss.controller.order;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.order.OrderSheetInfoDto;
import com.mysite.rmss.service.member.MemberService;
import com.mysite.rmss.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;

    @PostMapping("/orders/order")
    public String order(@ModelAttribute OrderSheetInfoDto orderSheetInfoDto,
                        @CurrentMember Member currentMember,
                        RedirectAttributes redirectAttributes) {

        if (currentMember == null) {
            return "redirect:/";
        }

        orderService.orderByCart(orderSheetInfoDto, currentMember.getUsername());

        redirectAttributes.addAttribute("memberName", currentMember.getUsername());
        return "redirect:/profile/{memberName}/orders";
    }

    // 판매 주문 리스트
    @GetMapping("/{shopPath}/orders")
    public String viewSalesOrderList(@PathVariable String shopPath,
                                     Model model) {
        // TODO: 관리자 권한

        model.addAttribute("salesOrderList", orderService.shopOrderList(shopPath));
        return "orders/shopSalesOrderList";
    }

    @GetMapping("/profile/{username}/orders")
    public String viewMemberOrderList(@PathVariable String username,
                                      @CurrentMember Member currentMember,
                                      Model model) {

        if (currentMember == null || !currentMember.getId().equals(memberService.findMemberInfoByUsername(username).getMemberId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "접근 권한이 없습니다.");
        }

        model.addAttribute("memberOrderList", orderService.memberOrderList(currentMember.getUsername()));
        return "orders/memberOrderList";
    }

    @GetMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId,
                              HttpServletRequest request) {

        orderService.cancelOrder(orderId);

        return "redirect:" + request.getHeader("Referer");
    }
}
