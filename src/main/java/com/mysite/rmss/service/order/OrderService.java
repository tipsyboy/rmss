package com.mysite.rmss.service.order;

import com.mysite.rmss.domain.cart.Cart;
import com.mysite.rmss.domain.item.Item;
import com.mysite.rmss.domain.item.OrderItem;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.domain.order.Order;
import com.mysite.rmss.domain.shop.Shop;
import com.mysite.rmss.dto.cart.AddItemToCartRequestDto;
import com.mysite.rmss.dto.order.OrderRequestDto;
import com.mysite.rmss.repository.item.ItemRepository;
import com.mysite.rmss.repository.member.MemberRepository;
import com.mysite.rmss.repository.order.OrderRepository;
import com.mysite.rmss.repository.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order (OrderRequestDto orderRequestDto) {
        // 필요: 고객정보 / 쇼핑몰 정보 / 주문 내역서
        // TODO: OrderRequestDto 를 웹 계층으로부터 전달 받는다.

        Member member = memberRepository.findByName(orderRequestDto.getMemberName())
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 유저가 없습니다. username=" + orderRequestDto.getMemberName()));

        Item item = itemRepository.findById(orderRequestDto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. itemId=" + orderRequestDto.getItemId()));

        Shop shop = item.getShop();


        // TODO: 배송정보 생성 -> Delivery class 정의

        // TODO: OrderItem 이 여러개인 경우로 바꾸기 - 장바구니 구현!
        // TODO: orderItem 생성 시 위에서 찾은 item 에 종속된 itemPrice 를 그대로 사용하지만, 나중에 할인률 같은 것을 계산할 때, 변경이 필요함  
        OrderItem orderItem = OrderItem.of(item, orderRequestDto); 
        Order order = Order.of(member, shop, orderItem);

        // TODO: repository 로 저장하면 끝
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void addItemToCart(AddItemToCartRequestDto addItemToCartRequestDto) {

        Member member = memberRepository.findByName(addItemToCartRequestDto.getMemberName())
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 유저가 없습니다. username=" + addItemToCartRequestDto.getMemberName()));

        Item item = itemRepository.findById(addItemToCartRequestDto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. itemId=" + addItemToCartRequestDto.getItemId()));

        Shop shop = item.getShop();

        OrderItem orderItem = OrderItem.ofByCartDto(item, addItemToCartRequestDto);
        member.getCart().addItem(orderItem);

    }
}
