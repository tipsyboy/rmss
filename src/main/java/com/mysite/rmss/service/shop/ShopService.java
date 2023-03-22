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

import java.util.List;
import java.util.stream.Collectors;

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

        // TODO: 이 과정에서 Shop itemList 를 Dto 로 변환 시켜서 ShopInfoResponseDto 에 추가하면 네트워크 한 번만 타도 되는거 아님?
        return new ShopInfoResponseDto(findShop);
    }

    public List<ShopInfoResponseDto> findAllShopList() {
        // TODO: paging 처리

        return shopRepository.findAllShop()
                .stream().map(Shop -> new ShopInfoResponseDto(Shop))
                .collect(Collectors.toList());
    }
}
