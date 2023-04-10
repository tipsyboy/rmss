package com.mysite.rmss.repository.cart;

import com.mysite.rmss.domain.cart.CartItem;
import com.mysite.rmss.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CartItemRepository {

    private final EntityManager em;

    public void delete(Long id) {
        em.remove(em.find(CartItem.class, id));
    }

    public List<CartItem> findAllCartItemsByMemberCartId(Long cartId) {
        return em.createQuery("select c from CartItem c where c.cart.id = :cartId", CartItem.class)
                .setParameter("cartId", cartId)
                .getResultList();
    }
}
