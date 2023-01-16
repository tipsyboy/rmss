package com.mysite.rmss.service.order;

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
        // 필요: 고객정보 / 쇼핑몰 정보 / 주문 내역서
        // TODO: memberId 로 Member 불러옴
        // TODO: shop 정보 불러옴
        // TODO: 받은 주문 내역서(dto)로 배송정보 생성
        // TODO: 위
        return 1L;
    }
}
