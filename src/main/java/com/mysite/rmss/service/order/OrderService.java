package com.mysite.rmss.service.order;

import com.mysite.rmss.domain.item.Item;
import com.mysite.rmss.domain.item.OrderItem;
import com.mysite.rmss.domain.member.Member;
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
        OrderRequestDto orderRequestDto = new OrderRequestDto(1L, 1L, 10000, 2);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. member_id=" + memberId));
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("해당 쇼핑몰이 없습니다 shopId=" + shopId));
        
        // 해당 아이템
        Item item = shop.getItems().stream()
                .filter(i -> i.getId().equals(orderRequestDto.getItemId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다. itemId=" + orderRequestDto.getItemId()));

        // TODO: 받은 주문 내역서(dto)로 배송정보 생성 -> Delivery class 정의

        // orderItem 생성
        OrderItem orderItem = OrderItem.of(item, orderRequestDto);

        // TODO: 생성한 orderItem 으로 order 를 생성함

        // TODO: repository 로 저장하면 끝
        return 1L;
    }
}
