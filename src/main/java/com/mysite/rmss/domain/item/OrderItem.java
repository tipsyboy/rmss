package com.mysite.rmss.domain.item;

import com.mysite.rmss.domain.cart.Cart;
import com.mysite.rmss.domain.order.Order;
import com.mysite.rmss.dto.cart.AddItemToCartRequestDto;
import com.mysite.rmss.dto.order.OrderRequestDto;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int orderPrice;

    private int count; // 수량

    // ===== 생성 메서드 ===== //
    public static OrderItem of(Item item, OrderRequestDto requestDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.item = item;
        orderItem.orderPrice = item.getPrice();
        orderItem.count = requestDto.getQuantity();

        return orderItem;
    }

    public static OrderItem ofByCartDto(Item item, AddItemToCartRequestDto requestDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.item = item;
        orderItem.orderPrice = item.getPrice();
        orderItem.count = requestDto.getQuantity();

        return orderItem;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
