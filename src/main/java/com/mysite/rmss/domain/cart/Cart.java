package com.mysite.rmss.domain.cart;

import com.mysite.rmss.domain.item.OrderItem;
import com.mysite.rmss.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Cart {

    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(mappedBy = "cart")
    private Member member;

    @OneToMany(mappedBy = "cart")
    private List<OrderItem> cartItems = new ArrayList<>();

    protected Cart() {}

    public static Cart from(Member member) {
        Cart cart = new Cart();
        cart.member = member;
        return cart;
    }
}
