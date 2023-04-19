package com.mysite.rmss.dto.order;

import com.mysite.rmss.domain.order.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemResponseDto {

    private Long orderItemId;
    private Long itemId;
    private String itemName;
    private Integer price;
    private Integer count;

    public OrderItemResponseDto(OrderItem entity) {
        this.orderItemId = entity.getId();
        this.itemId = entity.getItem().getId();
        this.itemName = entity.getItem().getItemName();
        this.price = entity.getOrderPrice();
        this.count = entity.getCount();
    }
}
