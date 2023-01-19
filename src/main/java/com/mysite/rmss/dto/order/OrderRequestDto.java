package com.mysite.rmss.dto.order;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private Long itemId;
    private Long memberId;
    private Integer price;
    private Integer quantity;

    public OrderRequestDto(Long itemId, Long memberId, Integer price, Integer quantity) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.price = price;
        this.quantity = quantity;
    }
}
