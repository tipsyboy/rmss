package com.mysite.rmss.domain.item;

import com.mysite.rmss.domain.shop.Shop;
import com.mysite.rmss.dto.item.ItemCreateForm;
import com.mysite.rmss.exception.NotEnoughStockException;
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


    // ===== =====//
    private void mappingShop(Shop shop) {
        this.shop = shop;
        shop.getItems().add(this);
    }

    // ===== 생성 ===== //
    protected Item() {}

    public static Item of(Shop shop, ItemCreateForm itemCreateForm) {
        Item item = new Item();
        item.mappingShop(shop);
        item.itemName = itemCreateForm.getItemName();
        item.description = itemCreateForm.getShortDescription();
        item.price = itemCreateForm.getPrice();
        item.stock = itemCreateForm.getStock();
        item.createDate = LocalDateTime.now();
        return item;
    }

    // ===== ===== //
    public void addStock(int quantity) {
        this.stock += quantity;
    }

    public void removeStock(int quantity) {
        if (this.stock - quantity < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }
        this.stock -= quantity;
    }
}