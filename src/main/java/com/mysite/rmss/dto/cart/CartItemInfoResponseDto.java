package com.mysite.rmss.dto.cart;

import com.mysite.rmss.domain.cart.CartItem;
import lombok.Getter;

@Getter
public class CartItemInfoResponseDto {

    private Long cartItemId;
    private String itemName;
    private String shopName;
    private Integer price;
    private Integer quantity;
    private Integer itemSumPrice;

    public CartItemInfoResponseDto(CartItem entity) {
        this.cartItemId = entity.getId();
        this.itemName = entity.getItem().getItemName();
        this.shopName = entity.getItem().getShop().getShopTitle();
        this.price = entity.getPrice();
        this.quantity = entity.getCount();
        this.itemSumPrice = price * quantity;
    }
}
