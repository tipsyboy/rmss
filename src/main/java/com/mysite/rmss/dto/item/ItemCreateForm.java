package com.mysite.rmss.dto.item;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ItemCreateForm {

    @NotBlank(message = "상품 이름을 입력해주세요.")
    @Length(min = 1, max = 30)
    private String itemName;

    @NotNull(message = "상품의 가격을 입력해 주세요.")
    @Range(min = 1, max = 2147483647)
    private Integer price;

    @NotNull(message = "판매 재고를 입력해 주세요.")
    @Range(min = 1, max = 2147483647)
    private Integer stock;

    @NotBlank(message = "상품 소개를 30자 이내로 입력해 주세요.")
    @Length(max = 30, message = "상품 소개를 30자 이내로 입력해 주세요.")
    private String shortDescription; // 짧은 소개


    // TODO: 아이템 이미지 추가

    // ===== 생성 ===== //
    public ItemCreateForm(String itemName, Integer price, Integer stock, String shortDescription) {
        this.itemName = itemName;
        this.price = price;
        this.stock = stock;
        this.shortDescription = shortDescription;
    }
}
