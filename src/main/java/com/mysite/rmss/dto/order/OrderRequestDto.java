package com.mysite.rmss.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class OrderRequestDto {

    private Long itemId;
    private String memberName; // member 로그인 아이디

    @NotNull
    private Integer quantity;

    public OrderRequestDto(Long itemId, String memberName, Integer quantity) {
        this.itemId = itemId;
        this.memberName = memberName;
        this.quantity = quantity;
    }
}
