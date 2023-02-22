package com.mysite.rmss.dto.order;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private Long itemId;
    private String memberName;
    private Integer price;
    private Integer quantity;

    public OrderRequestDto(Long itemId, String memberName, Integer price, Integer quantity) {
        this.itemId = itemId;
        this.memberName = memberName;
        this.price = price;
        this.quantity = quantity;
    }
}
