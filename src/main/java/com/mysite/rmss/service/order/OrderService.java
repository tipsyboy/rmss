package com.mysite.rmss.service.order;

import com.mysite.rmss.domain.item.Item;
import com.mysite.rmss.domain.item.OrderItem;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.domain.order.Order;
import com.mysite.rmss.domain.shop.Shop;
import com.mysite.rmss.dto.order.OrderRequestDto;
import com.mysite.rmss.repository.member.MemberRepository;
import com.mysite.rmss.repository.order.OrderRepository;
import com.mysite.rmss.repository.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public Long order (Long memberId, Long shopId) {
        // 필요: 고객정보 / 쇼핑몰 정보 / 주문 내역서
        // TODO: OrderRequestDto 를 웹 계층으로부터 전달 받는다.
        OrderRequestDto orderRequestDto = new OrderRequestDto(1L, "userA", 10000, 2);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. member_id=" + memberId));
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쇼핑몰이 없습니다 shopId=" + shopId));
        
        // 해당 아이템
        Item item = shop.getItems().stream()
                .filter(i -> i.getId().equals(orderRequestDto.getItemId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다. itemId=" + orderRequestDto.getItemId()));

        // TODO: 배송정보 생성 -> Delivery class 정의

        // TODO: OrderItem 이 여러개인 경우로 바꾸기 - 장바구니 구현!
        OrderItem orderItem = OrderItem.of(item, orderRequestDto);
        Order order = Order.of(member, shop, orderItem);

        // TODO: repository 로 저장하면 끝
        orderRepository.save(order);

        return order.getId();
    }
}
