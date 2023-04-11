package com.mysite.rmss.repository.cart;

import com.mysite.rmss.domain.cart.CartItem;
import com.mysite.rmss.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CartItemRepository {

    private final EntityManager em;

    public Optional<CartItem> findByItemIdAndCartId(Long itemId, Long cartId) {
        return em.createQuery("select ci from CartItem ci where ci.item.id = :itemId and ci.cart.id = :cartId", CartItem.class)
                .setParameter("itemId", itemId)
                .setParameter("cartId", cartId)
                .getResultList()
                .stream().findAny();
    }

    public void delete(Long id) {
        em.remove(em.find(CartItem.class, id));
    }

    public List<CartItem> findAllCartItemsByMemberCartId(Long cartId) {
        return em.createQuery("select ci from CartItem ci where ci.cart.id = :cartId", CartItem.class)
                .setParameter("cartId", cartId)
                .getResultList();
    }
}
