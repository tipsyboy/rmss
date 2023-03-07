package com.mysite.rmss.dto.cart;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class AddItemToCartRequestDto {

    @NotBlank
    private String memberName;

    @NotNull
    private Long itemId;

    @NotNull
    private Integer quantity;

    public AddItemToCartRequestDto(String memberName, Long itemId, Integer quantity) {
        this.memberName = memberName;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
