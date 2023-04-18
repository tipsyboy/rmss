package com.mysite.rmss.domain.order;

import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.domain.shop.Shop;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "orders")
@Entity
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 주문자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop; // 판매 쇼핑몰

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    protected Order() {}

    // ===== 연관관계 세팅 ===== //
    private void setMember(Member member) {
        this.member = member;
        member.getOrderList().add(this);
    }

    private void setShop(Shop shop) {
        this.shop = shop;
        shop.getOrderList().add(this);
    }

    private void addOrderItem(OrderItem orderItem) {
        this.orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    // ===== 생성 메서드 ===== //
    public static Order of(Member member, Shop shop, List<OrderItem> orderItems) {
        Order order = new Order();

        // 연관관계 메서드 - 생성자에 포함...?
        order.setMember(member);
        order.setShop(shop);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.orderStatus = OrderStatus.ORDER;
        order.orderDate = LocalDateTime.now();

        return order;
    }
}
