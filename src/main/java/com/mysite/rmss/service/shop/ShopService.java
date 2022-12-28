package com.mysite.rmss.service.shop;

import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.domain.shop.Shop;
import com.mysite.rmss.dto.shop.ShopInfoResponseDto;
import com.mysite.rmss.dto.shop.ShopOpenForm;
import com.mysite.rmss.repository.member.MemberRepository;
import com.mysite.rmss.repository.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ShopService {

    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public Long openShop(Long memberId, ShopOpenForm shopOpenForm) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. id=" + memberId));

        Shop shop = Shop.of(findMember, shopOpenForm);
        shopRepository.save(shop);
        return shop.getId();
    }

    public ShopInfoResponseDto getShopInfo(String shopPath) {
        Shop findShop = shopRepository.findByUrl(shopPath)
                .orElseThrow(() -> new IllegalArgumentException("쇼핑몰을 찾을 수 없습니다. url=" + shopPath));

        return new ShopInfoResponseDto(findShop);
    }

}
