package com.mysite.rmss.dto.order;

import com.mysite.rmss.domain.order.Order;
import com.mysite.rmss.domain.order.OrderItem;
import com.mysite.rmss.domain.order.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SalesOrderListDto {

    private Long orderId; // 주문번호
    private String memberName; // 주문자
    private LocalDateTime orderDate;// 주문 시간
    private List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>(); // 판매 아이템 목록
    private OrderStatus orderStatus;// 상태

    public SalesOrderListDto(Order entity) {
        this.orderId = entity.getId();
        this.memberName = entity.getMember().getUsername();
        this.orderDate = entity.getOrderDate();
        this.orderStatus = entity.getOrderStatus();
        for (OrderItem orderItem : entity.getOrderItemList()) {
            orderItemResponseDtoList.add(new OrderItemResponseDto(orderItem));
        }
    }
}
