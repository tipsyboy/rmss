package com.mysite.rmss.service.cart;

import com.mysite.rmss.domain.cart.Cart;
import com.mysite.rmss.domain.cart.CartItem;
import com.mysite.rmss.domain.item.Item;
import com.mysite.rmss.domain.item.OrderItem;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.cart.AddItemToCartRequestDto;
import com.mysite.rmss.dto.cart.CartItemInfoResponseDto;
import com.mysite.rmss.repository.cart.CartItemRepository;
import com.mysite.rmss.repository.item.ItemRepository;
import com.mysite.rmss.repository.member.MemberRepository;
import com.mysite.rmss.repository.orderItem.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CartService {

    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

    public List<CartItemInfoResponseDto> viewCartByMemberName(String memberName) {
        Member member = memberRepository.findByName(memberName)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다 name=" + memberName));

        return cartItemRepository.findAllCartItemsByMemberCartId(member.getCart().getId())
                .stream().map(CartItem -> new CartItemInfoResponseDto(CartItem))
                .collect(Collectors.toList());

//        return member.getCart().getCartItems()
//                .stream().map(CartItem -> new CartItemInfoResponseDto(CartItem))
//                .collect(Collectors.toList());
    }

    @Transactional
    public void addItemToCart(AddItemToCartRequestDto addItemToCartRequestDto) {
        Member member = memberRepository.findByName(addItemToCartRequestDto.getMemberName())
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 유저가 없습니다. username=" + addItemToCartRequestDto.getMemberName()));

        Item item = itemRepository.findById(addItemToCartRequestDto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. itemId=" + addItemToCartRequestDto.getItemId()));

        Optional<CartItem> cartItem = cartItemRepository.findByItemIdAndCartId(item.getId(), member.getCart().getId());
        if (cartItem.isEmpty()) {
            CartItem newCartItem = CartItem.ofByCartDto(item, addItemToCartRequestDto);
            member.getCart().addItem(newCartItem);
        } else {
            cartItem.get().increaseCount(addItemToCartRequestDto.getQuantity());
        }
    }

    @Transactional
    public void deleteCartItem(String memberName, Long cartItemId) {
        cartItemRepository.delete(cartItemId);
    }
}
