package com.mysite.rmss.service.order;

import com.mysite.rmss.dto.order.OrderRequestDto;
import com.mysite.rmss.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Long order () {
        OrderRequestDto orderRequestDto = new OrderRequestDto(1L, 1L, 2);

        // 필요: 고객정보 / 쇼핑몰 정보 / 주문 내역서
        // TODO: memberId 로 Member 불러옴
        // TODO: shop 정보 불러옴
        // TODO: 받은 주문 내역서(dto)로 배송정보 생성
        // TODO: 위 내용으로 orderItem 을 생성하고
        // TODO: 생성한 orderItem 으로 order 를 생성함
        // TODO: repository 로 저장하면 끝
        return 1L;
    }
}
