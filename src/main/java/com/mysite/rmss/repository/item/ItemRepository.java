package com.mysite.rmss.repository.item;

import com.mysite.rmss.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(em.find(Item.class, id));
    }

    public Optional<Item> findByName(String itemName) {
        return em.createQuery("select i from Item i where i.itemName = :itemName", Item.class)
                .setParameter("itemName", itemName)
                .getResultList()
                .stream().findAny();
    }

    public List<Item> findAllByShopPath(String shopPath) {
        return em.createQuery("select i from Item i where i.shop.url = :shopPath", Item.class)
                .setParameter("shopPath", shopPath)
                .getResultList();
    }
}
