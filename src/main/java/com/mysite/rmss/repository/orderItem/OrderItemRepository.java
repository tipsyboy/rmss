package com.mysite.rmss.repository.orderItem;

import com.mysite.rmss.domain.item.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class OrderItemRepository {

    private final EntityManager em;

    public void delete(Long id) {
        em.remove(em.find(OrderItem.class, id));
    }
}
