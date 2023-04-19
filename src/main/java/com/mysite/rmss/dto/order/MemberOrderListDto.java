package com.mysite.rmss.dto.order;

import com.mysite.rmss.domain.order.Order;
import com.mysite.rmss.domain.order.OrderItem;
import com.mysite.rmss.domain.order.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberOrderListDto {

    private Long orderId; // 주문번호
    private String shopTitle; // 판매 쇼핑몰
    private LocalDateTime orderDate;// 주문 시간
    private List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>(); // 구매 아이템 목록
    private OrderStatus orderStatus;// 상태

    public MemberOrderListDto(Order entity) {
        this.orderId = entity.getId();
        this.shopTitle = entity.getShop().getShopTitle();
        this.orderDate = entity.getOrderDate();
        this.orderStatus = getOrderStatus();
        for (OrderItem orderItem : entity.getOrderItemList()) {
            orderItemResponseDtoList.add(new OrderItemResponseDto(orderItem));
        }
    }
}
