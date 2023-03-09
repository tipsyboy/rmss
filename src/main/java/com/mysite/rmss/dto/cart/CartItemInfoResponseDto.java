package com.mysite.rmss.dto.cart;

import com.mysite.rmss.domain.item.OrderItem;
import lombok.Getter;

@Getter
public class CartItemInfoResponseDto {

    private String itemName;
    private String shopName;
    private Integer price;
    private Integer quantity;
    private Integer itemSumPrice;

    public CartItemInfoResponseDto(OrderItem entity) {
        this.itemName = entity.getItem().getItemName();
        this.shopName = entity.getItem().getShop().getShopTitle();
        this.price = entity.getOrderPrice();
        this.quantity = entity.getCount();
        this.itemSumPrice = price * quantity;
    }
}
