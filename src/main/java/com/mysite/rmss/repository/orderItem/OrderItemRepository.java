package com.mysite.rmss.repository.orderItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class OrderItemRepository {

    private final EntityManager em;

}
