package com.mysite.rmss.repository.cart;

import com.mysite.rmss.domain.cart.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class CartItemRepository {

    private final EntityManager em;

    public void delete(Long id) {
        em.remove(em.find(CartItem.class, id));
    }
}
