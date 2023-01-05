package com.mysite.rmss.dto.order;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private Long itemId;
    private Long memberId;
    private Integer quantity;
}
