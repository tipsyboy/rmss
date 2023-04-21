package com.mysite.rmss.domain.order;

import com.mysite.rmss.domain.cart.CartItem;
import com.mysite.rmss.domain.item.Item;
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

    public static OrderItem fromCartItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.item = cartItem.getItem();
        orderItem.orderPrice = cartItem.getPrice();
        orderItem.count = cartItem.getCount();

        cartItem.getItem().removeStock(cartItem.getCount());

        return orderItem;
    }

    // ===== 연관관계 세팅 ===== //
    public void setOrder(Order order) {
        this.order = order;
    }

    // ===== 엔티티 로직 ===== //
    public void cancel() {
        /**
         *  Order 취소시 재고 복귀
         */
        this.getItem().addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
