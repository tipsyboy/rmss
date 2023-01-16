package com.mysite.rmss.domain.shop;

import com.mysite.rmss.domain.Order.Order;
import com.mysite.rmss.domain.item.Item;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.shop.ShopOpenForm;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Shop {

    @Id @GeneratedValue
    @Column(name = "shop_id")
    private Long id;

    @OneToOne(mappedBy = "shop")
    private Member member;

    @Column(unique = true)
    private String shopTitle;

    @Column(unique = true)
    private String url;

    private String description; // 소개글
    private String phoneNumber; // 고객센터 번호

    @OneToMany(mappedBy = "shop")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Order> orderList = new ArrayList<>();

    // TODO: 쇼핑몰의 대표 카테고리 - 3개로 제한?
//    private Set<Category> categories = new HashSet<>();


    // ===== 연관관계? ===== //
    private void mappingMember(Member member) {
        this.member = member;
        member.setShop(this);
    }

    // ===== 생성 ===== //
    protected Shop() {}

    public static Shop of(Member member, ShopOpenForm shopOpenForm) {
        Shop shop = new Shop();
        shop.mappingMember(member);
        shop.shopTitle = shopOpenForm.getShopTitle();
        shop.url = shopOpenForm.getUrl();
        shop.description = shopOpenForm.getDescription();
        shop.phoneNumber = shop.getPhoneNumber();
        return shop;
    }


}
