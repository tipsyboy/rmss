package com.mysite.rmss.dto.order;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private Long itemId;
    private Long memberId;
    private Integer quantity;

    public OrderRequestDto(Long itemId, Long memberId, Integer quantity) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.quantity = quantity;
    }
}
