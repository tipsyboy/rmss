package com.mysite.rmss.repository.shop;

import com.mysite.rmss.domain.shop.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ShopRepository {

    private final EntityManager em;

    public Long save(Shop shop) {
        em.persist(shop);
        return shop.getId();
    }

    public Optional<Shop> findById(Long id) {
        return Optional.ofNullable(em.find(Shop.class, id));
    }

    // Shop 이름으로 찾기
    public Optional<Shop> findByTitle(String title) {
        return em.createQuery("select s from Shop s where s.shopTitle = :title", Shop.class)
                .setParameter("title", title)
                .getResultList()
                .stream().findAny();
    }

    public Optional<Shop> findByUrl(String shopPath) {
        return em.createQuery("select s from Shop s where s.url = :shopPath", Shop.class)
                .setParameter("shopPath", shopPath)
                .getResultList()
                .stream().findAny();
    }

    public List<Shop> findAllShop() {
        return em.createQuery("select s from Shop s", Shop.class)
                .getResultList();
    }

    public boolean existsByShopTitle(String shopTitle) {
        List<Long> result = em.createQuery("select s.id from Shop s where s.shopTitle = :shopTitle", Long.class)
                .setParameter("shopTitle", shopTitle)
                .getResultList();

        return result.size() > 0;
    }

    public boolean existsByUrl(String url) {
        List<Long> result = em.createQuery("select s.id from Shop s where s.url = :url", Long.class)
                .setParameter("url", url)
                .getResultList();

        return result.size() > 0;
    }
}
