package com.mysite.rmss.domain.shop;

import com.mysite.rmss.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private String path;

    private String description;


    // TODO: 쇼핑몰의 아이템 리스트
//    private List<Item> items = new ArrayList<>();
    // TODO: 쇼핑몰의 대표 카테고리 - 3개로 제한?
//    private Set<Category> categories = new HashSet<>();


    // ===== 생성 ===== //
    protected Shop() {}


}
