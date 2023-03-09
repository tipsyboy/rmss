package com.mysite.rmss.service.cart;

import com.mysite.rmss.domain.item.OrderItem;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.cart.CartItemInfoResponseDto;
import com.mysite.rmss.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartService {

    private final MemberRepository memberRepository;

    public List<CartItemInfoResponseDto> viewCartByMemberName(String memberName) {
        Member member = memberRepository.findByName(memberName)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다 name=" + memberName));

        return member.getCart().getCartItems()
                .stream().map(OrderItem -> new CartItemInfoResponseDto(OrderItem))
                .collect(Collectors.toList());
    }
}
