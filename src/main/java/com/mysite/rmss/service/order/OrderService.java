package com.mysite.rmss.service.order;

import com.mysite.rmss.domain.cart.CartItem;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.domain.order.Order;
import com.mysite.rmss.domain.order.OrderItem;
import com.mysite.rmss.domain.shop.Shop;
import com.mysite.rmss.dto.order.OrderSheetInfoDto;
import com.mysite.rmss.dto.order.SalesOrderListDto;
import com.mysite.rmss.repository.cart.CartItemRepository;
import com.mysite.rmss.repository.member.MemberRepository;
import com.mysite.rmss.repository.order.OrderRepository;
import com.mysite.rmss.repository.shop.ShopRepository;
import com.mysite.rmss.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;
    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void orderByCart(OrderSheetInfoDto orderSheetInfoDto,
                            String memberName) {
        Member member = memberRepository.findByName(memberName)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다 username=" + memberName));

        Map<String, List<OrderItem>> categorizedOrderItemsByShop = convertCartItemToOrderItemByShopTitle(orderSheetInfoDto);
        for (String shopTitle : categorizedOrderItemsByShop.keySet()) {
            log.info("shopTitle={}", shopTitle);
            Shop findShop = shopRepository.findByTitle(shopTitle)
                    .orElseThrow(() -> new IllegalArgumentException("쇼핑몰을 찾을 수 없습니다. shopTitle=" + shopTitle));

            Order order = Order.of(member, findShop, categorizedOrderItemsByShop.get(shopTitle));
            orderRepository.save(order);
        }
    }

//    public List<SalesOrderListDto> salesOrderList() {
//
//    }

    private Map<String, List<OrderItem>> convertCartItemToOrderItemByShopTitle(OrderSheetInfoDto orderSheetInfoDto) {
        List<CartItem> selectedCartItems = cartItemRepository.findBySelectedIdList(orderSheetInfoDto.getIdList());

        Map<String, List<OrderItem>> orderItems = new HashMap<>();
        for (CartItem cartItem : selectedCartItems) {
            if (!orderItems.containsKey(cartItem.getShopTitle())) {
                orderItems.put(cartItem.getShopTitle(), new ArrayList<>());
            }
            orderItems.get(cartItem.getShopTitle()).add(OrderItem.fromCartItem(cartItem));
            cartItemRepository.delete(cartItem.getId()); // TODO: 개선 여지가 있는데..
        }

        return orderItems;
    }
}
