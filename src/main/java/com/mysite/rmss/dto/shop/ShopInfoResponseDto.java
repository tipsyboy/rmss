package com.mysite.rmss.dto.shop;

import com.mysite.rmss.domain.shop.Shop;
import lombok.Getter;

@Getter
public class ShopInfoResponseDto {

    private String shopTitle;
    private String url;
    private String description;
    private String phoneNumber;

    public ShopInfoResponseDto(Shop entity) {
        this.shopTitle = entity.getShopTitle();
        this.url = entity.getUrl();
        this.description = entity.getDescription();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
