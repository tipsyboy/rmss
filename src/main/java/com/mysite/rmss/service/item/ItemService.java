package com.mysite.rmss.service.item;

import com.mysite.rmss.domain.item.Item;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.domain.shop.Shop;
import com.mysite.rmss.dto.item.ItemCreateForm;
import com.mysite.rmss.repository.item.ItemRepository;
import com.mysite.rmss.repository.member.MemberRepository;
import com.mysite.rmss.repository.shop.ShopRepository;
import com.mysite.rmss.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addItem(Long memberId, ItemCreateForm itemCreateForm) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다 id=" + memberId));

        Shop shop = member.getShop();
        Item item = Item.of(shop, itemCreateForm);
        itemRepository.save(item);
    }
}
