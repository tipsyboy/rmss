package com.mysite.rmss.dto.shop;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class ShopOpenForm {

    @NotBlank(message = "쇼핑몰 이름을 입력해주세요.")
    @Length(min = 2, max = 20)
    private String shopTitle; // 쇼핑몰 이름

    @NotBlank(message = "쇼핑몰 URL을 3자 이상 20자 이하로 입력해주세요.")
    @Length(min = 3, max = 20, message = "쇼핑몰 URL을 3자 이상 20자 이하로 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String url;

    @NotBlank(message = "대표 고객센터 번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{10,11}", message = "10 ~ 11자리의 숫자만 입력 가능합니다")
    private String phoneNumber;

    @NotBlank(message = "짧은 쇼핑몰 소개글을 100자 이내로 입력해 주세요.")
    @Length(max = 100, message = "짧은 쇼핑몰 소개글을 100자 이내로 입력해 주세요.")
    private String description;

    public ShopOpenForm(String shopTitle, String url, String description, String phoneNumber) {
        this.shopTitle = shopTitle;
        this.url = url;
        this.description = description;
        this.phoneNumber = phoneNumber;
    }
}
