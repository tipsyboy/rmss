package com.mysite.rmss.service.item;

import com.mysite.rmss.domain.item.Item;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.domain.shop.Shop;
import com.mysite.rmss.dto.item.ItemCreateForm;
import com.mysite.rmss.dto.item.ItemResponseDto;
import com.mysite.rmss.repository.item.ItemRepository;
import com.mysite.rmss.repository.member.MemberRepository;
import com.mysite.rmss.repository.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ItemService {

//    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addItem(Long memberId, ItemCreateForm itemCreateForm) throws IOException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다 id=" + memberId));

        MultipartFile imgFile = itemCreateForm.getImgFile();
        String ogImgName = imgFile.getOriginalFilename();
        String imgPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";
        String imgName = UUID.randomUUID() + "_" + ogImgName;
        imgFile.transferTo(new File(imgPath, imgName));


        Shop shop = member.getShop();
        Item item = Item.of(shop, itemCreateForm, imgName);
        itemRepository.save(item);
    }

    public List<ItemResponseDto> findAllByShopPath(String shopPath) {
        return itemRepository.findAllByShopPath(shopPath)
                .stream().map(ItemResponseDto::new)
                .collect(Collectors.toList());
    }

    public ItemResponseDto findById(Long itemId) {
        return new ItemResponseDto(itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. itemId=" + itemId)));
    }

//    public List<ItemResponseDto> findAllItemByShopPathEntityRep(String shopPath) {
//        // 이건 어떰?
//
//        Shop shop = shopRepository.findByUrl(shopPath)
//                .orElseThrow(() -> new IllegalArgumentException("쇼핑몰을 찾을 수 없음"));
//
//        return shop.getItems()
//                .stream().map(ItemResponseDto::new)
//                .collect(Collectors.toList());
//    }
}
