package com.mysite.rmss.domain.item;

import com.mysite.rmss.domain.shop.Shop;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;
    private String description;
    private Integer price;
    private Integer stock;
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    // ===== 생성 ===== //
    protected Item() {}

}