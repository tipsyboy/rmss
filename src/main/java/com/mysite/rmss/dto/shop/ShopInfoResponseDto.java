package com.mysite.rmss.dto.shop;

import com.mysite.rmss.domain.shop.Shop;
import com.mysite.rmss.file.UploadFile;
import lombok.Getter;

@Getter
public class ShopInfoResponseDto {

    private String shopTitle;
    private String url;
    private String description;
    private String phoneNumber;
    private UploadFile shopImage;

    public ShopInfoResponseDto(Shop entity) {
        this.shopTitle = entity.getShopTitle();
        this.url = entity.getUrl();
        this.description = entity.getDescription();
        this.phoneNumber = entity.getPhoneNumber();
        this.shopImage = entity.getShopImage();
    }
}
