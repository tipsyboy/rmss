package com.mysite.rmss.domain.item;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ItemImage {

    private String uploadImageName; // 유저가 업로드한 이미지 파일명
    private String storeImageName; // 서버에 내부 관리명

    public ItemImage(String uploadImageName, String storeImageName) {
        this.uploadImageName = uploadImageName;
        this.storeImageName = storeImageName;
    }
}
