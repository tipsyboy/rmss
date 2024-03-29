package com.mysite.rmss.domain.cart;

import com.mysite.rmss.domain.item.Item;
import com.mysite.rmss.dto.cart.AddItemToCartRequestDto;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class CartItem {

    @Id @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private String shopTitle;
    private int price;
    private int count;

    public static CartItem ofByCartDto(Item item, AddItemToCartRequestDto requestDto) {
        CartItem cartItem = new CartItem();
        cartItem.item = item;
        cartItem.shopTitle = item.getShop().getShopTitle();
        cartItem.price = item.getPrice();
        cartItem.count = requestDto.getQuantity();

        return cartItem;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void increaseCount(int q) {
        // 이미 장바구니에 포함된 상품의 경우 count 증가
        this.count += q;
    }
}
