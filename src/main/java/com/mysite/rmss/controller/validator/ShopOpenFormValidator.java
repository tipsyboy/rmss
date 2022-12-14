package com.mysite.rmss.controller.validator;

import com.mysite.rmss.domain.shop.Shop;
import com.mysite.rmss.dto.shop.ShopOpenForm;
import com.mysite.rmss.repository.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class ShopOpenFormValidator implements Validator {

    private final ShopRepository shopRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ShopOpenForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ShopOpenForm shopOpenForm = (ShopOpenForm) target;

        if (shopRepository.existsByShopTitle(shopOpenForm.getShopTitle())) {
            errors.rejectValue("shopTitle", "duplicatedShopTitle",
                    "이미 사용중인 쇼핑몰 이름입니다.");
        }

        if (shopRepository.existsByUrl(shopOpenForm.getUrl())) {
            errors.rejectValue("url", "duplicatedUrl");
        }
    }
}
