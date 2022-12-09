package com.mysite.rmss.dto.shop;

import lombok.Getter;

@Getter
public class ShopOpenForm {

    private String shopTitle; // 쇼핑몰 이름
    private String url; // TODO: 쇼핑몰 주소 소문자만 가능
    private String phoneNumber;
    private String description;

    public ShopOpenForm(String shopTitle, String url, String description, String phoneNumber) {
        this.shopTitle = shopTitle;
        this.url = url;
        this.description = description;
        this.phoneNumber = phoneNumber;
    }
}
